package org.cau.shoppingmall.controller;

import lombok.RequiredArgsConstructor;
import org.cau.shoppingmall.dto.inquiry.OneToOneInquiryForm;
import org.cau.shoppingmall.service.RedisTestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
@Controller
@RequiredArgsConstructor
public class TestController {

    private final RedisTestService redisTestService;

    @GetMapping("/test/oneToOneInquiry")
    public String test(Model model) {

        model.addAttribute("oneToOneInquiryForm",new OneToOneInquiryForm());

        return "test/inquiry/inquiry_form";
    }

    @PostMapping("/test/inquiry/oneToOne")
    public String test2(Model model, List<MultipartFile> files) {
        return "ok";
    }

    @GetMapping("/test/get")
    @ResponseBody
    public String test2() {
        redisTestService.getRankingList();
        return "ok";
    }
}
