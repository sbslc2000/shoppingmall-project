package org.cau.shoppingmall.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cau.shoppingmall.dto.Users.UserDto;
import org.cau.shoppingmall.dto.inquiry.OneToOneInquiryDto;
import org.cau.shoppingmall.dto.inquiry.OneToOneInquiryForm;
import org.cau.shoppingmall.dto.review.ReviewForm;
import org.cau.shoppingmall.exception.NoAuthInfoFoundException;
import org.cau.shoppingmall.service.ImageService;
import org.cau.shoppingmall.service.LoginService;
import org.cau.shoppingmall.service.OneToOneInquiryService;
import org.cau.shoppingmall.service.UserService;
import org.hibernate.mapping.OneToOne;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequiredArgsConstructor
public class InquiryController {

    private final OneToOneInquiryService inquiryService;
    private final ImageService imageService;
    private final UserService userService;
    private final LoginService loginService;

    @GetMapping("/inquiry-form")
    public String inquiryForm(Model model, HttpSession session) {


        model.addAttribute("oneToOneInquiryform", new OneToOneInquiryForm());

        return "inquiry/inquiry_form";
    }

    @PostMapping("/inquiry")
    public String createInquiry(RedirectAttributes redirect, HttpSession session,
                                List<MultipartFile> imgList, @Validated OneToOneInquiryForm form,
                                BindingResult bindingResult) {

        //유효성 검사
        if(bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
        }
        try{
            Long userId = loginService.getUserId(session);
            inquiryService.create(form,userId,imgList);

        } catch (NoAuthInfoFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return "redirect:/inquiries";
    }

    @GetMapping("/inquiries")
    public String inquiryHandler(Model model,HttpSession session) {

        try {
            Long userId = loginService.getUserId(session);

            UserDto user = userService.get(userId);

            if(user.getAuthority().getId().equals(1L)) {
                List<OneToOneInquiryDto> inquiryList = inquiryService.getByUserId(userId);
                model.addAttribute("inquiryList", inquiryList);
            } else if (user.getAuthority().getId().equals(2L) || user.getAuthority().getId().equals(3L)) {
                List<OneToOneInquiryDto> inquiryList = inquiryService.getAllInquiries();
                model.addAttribute("inquiryList", inquiryList);
            }

            return "inquiry/inquiry";
        } catch (NoAuthInfoFoundException e) {
            e.printStackTrace();
            return "redirect:/login";
        }
    }

    @GetMapping("/inquiry/{inquiryId}")
    public String showInquiry(Model model, HttpSession session,
                              @PathVariable Long inquiryId, RedirectAttributes redirect) {

        try {
            Long userId = loginService.getUserId(session);
            UserDto user = userService.get(userId);

            OneToOneInquiryDto oneToOneInquiryDto = inquiryService.get(inquiryId);
            Long userAuthorityId = user.getAuthority().getId();

            if((userAuthorityId.equals(2L) || userAuthorityId.equals(3L)) || userId.equals(oneToOneInquiryDto.getUser().getId()) ) {
                //관리자일때 || 질의자 본인일때

                model.addAttribute("inquiry",oneToOneInquiryDto);
                return "inquiry/inquiries";
            } else {
                //권한이 없음


                return "redirect:/inquiries";

            }

        } catch (NoAuthInfoFoundException e) {
            throw new RuntimeException(e);
        } catch (NoSuchElementException e)  {
            e.printStackTrace();
        }

        return null;
    }
}
