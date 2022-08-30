package org.cau.shoppingmall.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cau.shoppingmall.dto.inquiry.OneToOneInquiryDto;
import org.cau.shoppingmall.dto.inquiry.OneToOneInquiryForm;
import org.cau.shoppingmall.dto.review.ReviewForm;
import org.cau.shoppingmall.exception.NoAuthInfoFoundException;
import org.cau.shoppingmall.service.ImageService;
import org.cau.shoppingmall.service.LoginService;
import org.cau.shoppingmall.service.OneToOneInquiryService;
import org.hibernate.mapping.OneToOne;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequiredArgsConstructor
public class InquiryController {

    private final OneToOneInquiryService inquiryService;
    private final ImageService imageService;
    private final LoginService loginService;

    @GetMapping("/inquiry-form")
    public String inquiryForm(Model model, HttpSession session) {


        model.addAttribute("oneToOneInquiryform", new OneToOneInquiryForm());

        return "inquiry/inquiry_form";
    }

    @PostMapping("/inquiry")
    public String createInquiry(RedirectAttributes redirect, HttpSession session,
                                List<MultipartFile> imgList, OneToOneInquiryForm form) {

        try{
            Long userId = loginService.getUserId(session);
            inquiryService.create(form,userId,imgList);

        } catch (NoAuthInfoFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return "redirect:/inquiry";
    }

    @GetMapping("/inquiry")
    public String inquiryHandler(Model model,HttpSession session) {

        try {
            Long userId = loginService.getUserId(session);

            List<OneToOneInquiryDto> inquiryList = inquiryService.getByUserId(userId);
            model.addAttribute("inquiryList", inquiryList);

            return "inquiry/inquiry";
        } catch (NoAuthInfoFoundException e) {
            e.printStackTrace();
            return "redirect:/login";
        }

    }
}
