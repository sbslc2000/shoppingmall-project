package org.cau.shoppingmall.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cau.shoppingmall.common.dto.MessageDto;
import org.cau.shoppingmall.common.handler.MessageHandler;
import org.cau.shoppingmall.dto.Users.LoginForm;
import org.cau.shoppingmall.dto.inquiry.OneToOneInquiryDto;
import org.cau.shoppingmall.dto.inquiry.OneToOneInquiryForm;
import org.cau.shoppingmall.exception.notfound.NoAuthInfoFoundException;
import org.cau.shoppingmall.exception.notfound.NoInquiryTypeFoundException;
import org.cau.shoppingmall.exception.notfound.UserNotFoundException;
import org.cau.shoppingmall.service.ImageService;
import org.cau.shoppingmall.service.LoginService;
import org.cau.shoppingmall.service.OneToOneInquiryService;
import org.cau.shoppingmall.service.UserService;
import org.cau.shoppingmall.user.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequiredArgsConstructor
@Api(tags = {"문의 페이지 정보"})
public class InquiryController {

    private final OneToOneInquiryService inquiryService;
    private final LoginService loginService;

    @GetMapping("/inquiry-form")
    @ApiOperation(value = "1대1 문의 작성 페이지를 반환합니다. parameter 필요 없음!")
    public String inquiryForm(Model model, HttpSession session) {

        try {
            loginService.getLoginedUserData(session);
        } catch (NoAuthInfoFoundException e) {
            MessageDto message = new MessageDto("로그인이 필요한 서비스입니다.", "/login", RequestMethod.GET, null);
            return MessageHandler.showMessageAndRedirect(message,model);
        }

        model.addAttribute("oneToOneInquiryForm", new OneToOneInquiryForm());
        return "onetooneinquiry/inquiry_write";
    }

    @PostMapping("/inquiry")
    @ApiOperation(value = "1대1 문의를 생성합니다.")
    public String createInquiry(Model model, RedirectAttributes redirect, HttpSession session,
                                @ApiParam(value="1대1 문의 첨부 파일") List<MultipartFile> imgList,
                                @ApiParam(value="1대1 문의 form")
                                    @Validated OneToOneInquiryForm form,
                                BindingResult bindingResult) throws UserNotFoundException, NoInquiryTypeFoundException {


        System.out.println(imgList.size());

        //유효성 검사
        if(bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
            MessageDto message = new MessageDto("로그인이 필요한 서비스입니다.", "/login", RequestMethod.GET, null);
            return MessageHandler.showMessageAndRedirect(message,model);
        }
        try{
            UserDetails userDetails = loginService.getLoginedUserData(session);
            inquiryService.create(form, userDetails.getId(), imgList);

        } catch (NoAuthInfoFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return "redirect:/inquiries";
    }

    @GetMapping("/inquiry/imgTest/{inquiryId}")
    public String inquiryTest(Model model, @PathVariable Long inquiryId) {
        OneToOneInquiryDto inquiry = inquiryService.get(inquiryId);
        model.addAttribute("inquiry",inquiry);

        return "onetooneinquiry/inquiry_post";
    }

    @GetMapping("/inquiries")
    public String inquiryHandler(Model model,HttpSession session) {

        try {
            UserDetails userDetails =  loginService.getLoginedUserData(session);
            Long userAuthorityId = userDetails.getAuthority().getId();

            if(userAuthorityId.equals(1L)) {
                List<OneToOneInquiryDto> inquiryList = inquiryService.getByUserId(userDetails.getId());
                model.addAttribute("inquiryList", inquiryList);
            } else {
                List<OneToOneInquiryDto> inquiryList = inquiryService.getAllInquiries();
                model.addAttribute("inquiryList", inquiryList);
            }

            return "onetooneinquiry/inquiry";
        } catch (NoAuthInfoFoundException e) {
            e.printStackTrace();
            MessageDto message = new MessageDto("로그인이 필요한 서비스입니다.", "/login", RequestMethod.GET, null);
            return MessageHandler.showMessageAndRedirect(message,model);
        }
    }

    @GetMapping("/inquiry/{inquiryId}")
    public String showInquiry(Model model, HttpSession session,
                              @PathVariable Long inquiryId, RedirectAttributes redirect) {

        try {
            UserDetails userDetails = loginService.getLoginedUserData(session);

            OneToOneInquiryDto oneToOneInquiryDto = inquiryService.get(inquiryId);
            Long userAuthorityId = userDetails.getAuthority().getId();

            if((userAuthorityId.equals(2L) || userAuthorityId.equals(3L)) ||
                    userDetails.getId().equals(oneToOneInquiryDto.getUser().getId()) ) {
                //관리자일때 || 질의자 본인일때

                model.addAttribute("inquiry",oneToOneInquiryDto);
                return "inquiry/inquiry";
            } else {
                //권한이 없음


                return "redirect:/inquiries";

            }

        } catch (NoAuthInfoFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
