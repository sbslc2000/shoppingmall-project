package org.cau.shoppingmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {

    @GetMapping("/login")
    public String loginPage() {

        return "user/login";
    }

    @GetMapping("/user/join")
    public String joinPage() {

        return "user/join";
    }


}
