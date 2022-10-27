package org.cau.shoppingmall.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cau.shoppingmall.dto.Users.AuthInfo;
import org.cau.shoppingmall.dto.Users.LoginForm;
import org.cau.shoppingmall.dto.Users.UserForm;
import org.cau.shoppingmall.dto.Users.UserUpdateForm;
import org.cau.shoppingmall.dto.item.ItemDto;
import org.cau.shoppingmall.entity.user.User;
import org.cau.shoppingmall.exception.LoginFailedException;
import org.cau.shoppingmall.repository.UserRepository;
import org.cau.shoppingmall.service.EmailService;
import org.cau.shoppingmall.service.ItemService;
import org.cau.shoppingmall.service.LoginService;
import org.cau.shoppingmall.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AccountController {


    private final LoginService loginService;
    private final ItemService itemService;
    private final UserService userService;

    private final EmailService emailService;

    /*
    * 로그인 페이지를 반환한다.
    * */
    @GetMapping("/login")
    public String loginPage(Model model,
                            @RequestParam(required = false) Boolean error,
                            @RequestParam(required = false) String msg) {

        model.addAttribute("error",error);
        model.addAttribute("msg",msg);
        model.addAttribute("loginForm",new LoginForm());
        return "user/login";
    }

    /*
     * 로그인 처리를 한다.
     * */
    @PostMapping("/login")
    public String loginHandler(LoginForm form, HttpSession session, RedirectAttributes redirect) {

        try {
            AuthInfo login = loginService.login(form, session);
        } catch (LoginFailedException e) {
            log.info("loginFailed : {}",e.getMessage());
            e.printStackTrace();
            redirect.addFlashAttribute("loginForm",form);
            try {
                return "redirect:/login?error=true&msg="+ URLEncoder.encode(e.getMessage(),"UTF-8");
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
        }

        List<ItemDto> hotItems = itemService.getHot4Items();

        redirect.addFlashAttribute("hotItems",hotItems);
        return "redirect:/home";
    }


    /*
    * 회원가입 페이지를 반환한다.
    * */
    @GetMapping("/user/join")
    public String joinPage(Model model) {

        model.addAttribute("userForm",new UserForm());
        return "user/join";
    }

    // 회원가입 페이지의 내용을 넘겨주는 역할이 필요
    @PostMapping("/users")
    public String createUser(@Validated @ModelAttribute UserForm userForm,BindingResult bindingResult, RedirectAttributes redirect) {
        log.info("postMapping : /users");
        if(bindingResult.hasErrors()) {
            log.info("bindingResult.hasErrors");
            List<String> bindingErrors = bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
            for(String errors : bindingErrors) {
                log.info(errors);
            }
            redirect.addFlashAttribute("bindingErrors",bindingErrors);
            redirect.addFlashAttribute("userForm",userForm);

            log.info("before Redirect");
            return "redirect:/user/join";
        }

        try {
            User user = userService.create(userForm);
            emailService.sendWelcomeEmail(user.getEmail(),"sbscl");
        } catch (IllegalArgumentException e) {
            List<String> bindingErrors = new ArrayList<>();
            bindingErrors.add(e.getMessage());
            redirect.addFlashAttribute("bindingErrors",bindingErrors);
            redirect.addFlashAttribute("userForm",userForm);
            return "redirect:/user/join";
        }


        redirect.addFlashAttribute("loginForm",new LoginForm());
        return "redirect:/login";

    }

    /*
    *
    * TODO:아이디 비밀번호 찾기 페이지를 반환한다.
    * */
    @GetMapping("/users/find")
    public String findIdOrPassword() {

        return "users/find";
    }


    /*
    * todo: 유저 업데이트 정보를 받아 정보를 수정한다.
    * */
    @PostMapping("/users/update")
    public String userUpdate(UserUpdateForm form,RedirectAttributes redirect) {

        return "redirect:/mypage";
    }



}
