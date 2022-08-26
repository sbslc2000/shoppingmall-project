package org.cau.shoppingmall.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cau.shoppingmall.dto.inquiry.OneToOneInquiryForm;
import org.cau.shoppingmall.service.ImageService;
import org.cau.shoppingmall.service.RedisTestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class TestController {

    @Value("${file.dir}")
    private String fileDir;

    private final ImageService imageService;
    private final RedisTestService redisTestService;

    @GetMapping("/test/oneToOneInquiry")
    public String test(Model model) {

        model.addAttribute("oneToOneInquiryForm",new OneToOneInquiryForm());

        return "test/inquiry/inquiry_form";
    }


    @PostMapping("/test/inquiry/oneToOne")
    public String test2(Model model, @RequestParam List<MultipartFile> files,
                        @ModelAttribute("oneToOneInquiryForm") OneToOneInquiryForm form) {

        log.info("multipartFile={}",files);
        try{
            List<String> imgs = imageService.storeOneToOneInquiryImages(files);

            System.out.println(imgs.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "ok";
    }


    @GetMapping("/test/get")
    @ResponseBody
    public String test2() {
        redisTestService.getRankingList();
        return "ok";
    }
}
