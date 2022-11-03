package org.cau.shoppingmall.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cau.shoppingmall.common.dto.MessageDto;
import org.cau.shoppingmall.common.handler.MessageHandler;
import org.cau.shoppingmall.dto.Users.AuthInfo;
import org.cau.shoppingmall.dto.Users.LoginForm;
import org.cau.shoppingmall.dto.Users.UserForm;
import org.cau.shoppingmall.dto.Users.UserUpdateForm;
import org.cau.shoppingmall.dto.item.ItemDto;
import org.cau.shoppingmall.entity.user.User;
import org.cau.shoppingmall.exception.DuplicateUserIdException;
import org.cau.shoppingmall.exception.LoginFailedException;
import org.cau.shoppingmall.repository.UserRepository;
import org.cau.shoppingmall.service.ItemService;
import org.cau.shoppingmall.service.LoginService;
import org.cau.shoppingmall.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AccountController {


    private final LoginService loginService;
    private final ItemService itemService;
    private final UserService userService;


    /*
    * 로그인 페이지를 반환한다.
    * */
    @GetMapping("/login")
    public String loginPage(Model model, @ModelAttribute LoginForm loginForm) {

        if(loginForm != null) {
            model.addAttribute("loginForm",new LoginForm(loginForm.getUserId(),null));
        } else {
            model.addAttribute("loginForm",new LoginForm(null,null));
        }

        return "user/login";
    }

    /*
     * 로그인 처리를 한다.
     * */
    @PostMapping("/login")
    public String loginHandler(Model model,LoginForm form, HttpSession session, RedirectAttributes redirect) {

        log.info("post /login");
        try {
            AuthInfo login = loginService.login(form, session);
        } catch (LoginFailedException e) {

            log.info("LoginFailedException ");

            MessageDto message = new MessageDto(e.getMessage(),"/login", RequestMethod.GET,null);
            return MessageHandler.showMessageAndRedirect(message,model);
            //return loginFailedHandler(e,redirect,form);
        }


        // 로그인 성공시
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
    public String createUser(Model model,@Validated @ModelAttribute UserForm userForm,BindingResult bindingResult) {

        //binding error 발생 시
        //error 객체와 userForm을 담아서 회원가입 페이지로 리다이렉트
        if(bindingResult.hasErrors()) {
            log.info("bindingResult.hasErrors");
            List<String> bindingErrors = bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
            MessageDto message = new MessageDto(bindingErrors.get(0), "/user/join", RequestMethod.GET, null);
            return MessageHandler.showMessageAndRedirect(message,model);
        }

        // 회원 가입 진행
        try {
            User user = userService.create(userForm);
        } catch (DuplicateUserIdException e) {

            MessageDto message = new MessageDto(e.getMessage(), "/user/join", RequestMethod.GET, null);
            return MessageHandler.showMessageAndRedirect(message,model);
        }


        //회원가입 성공시 로그인 페이지 반환
        MessageDto message = new MessageDto("회원가입에 성공했습니다.","/login",RequestMethod.GET, null);
        return MessageHandler.showMessageAndRedirect(message,model);
    }

    /*
    *
    * TODO:아이디 비밀번호 찾기 페이지를 반환한다.
    * */
    @GetMapping("/users/find")
    public String findIdOrPassword() {

        return "user/find";
    }



    /*
    * todo: 유저 업데이트 정보를 받아 정보를 수정한다.
    * */
    @PostMapping("/users/update")
    public String userUpdate(UserUpdateForm form,RedirectAttributes redirect) {

        return "redirect:/mypage";
    }



}
