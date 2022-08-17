package org.cau.shoppingmall.controller;

import lombok.RequiredArgsConstructor;
import org.cau.shoppingmall.service.RedisTestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class TestController {

    private final RedisTestService redisTestService;

    @GetMapping("/test/add")
    @ResponseBody
    public String test(String searchWord) {
        redisTestService.addDataInRedis(searchWord);
        return "ok";
    }

    @GetMapping("/test/get")
    @ResponseBody
    public String test2() {
        redisTestService.getRankingList();
        return "ok";
    }
}
