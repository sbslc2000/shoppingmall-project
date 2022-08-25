package org.cau.shoppingmall.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cau.shoppingmall.dto.inquiry.OneToOneInquiryForm;
import org.cau.shoppingmall.dto.review.ReviewForm;
import org.cau.shoppingmall.service.ImageService;
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

    private OneToOneInquiryService inquiryService;
    private ImageService imageService;

    @GetMapping("/inquiry-form")
    public String inquiryForm(Model model, HttpSession session) {


        model.addAttribute("oneToOneInquiryform", new OneToOneInquiryForm());

        return "inquiry/inquiry_form";
    }

    @PostMapping("/inquiry")
    public String createInquiry(RedirectAttributes redirect, HttpSession session,
                                List<MultipartFile> imgList, OneToOneInquiryForm form) {

        Long userId = 0L;
        //session 에서 userId를 가져온다.

        try {
            inquiryService.create(form,userId,imgList);
        } catch(IOException e) {
            e.printStackTrace();
        }


        return "redirect:/inquiry/inquiry";


    }
}
