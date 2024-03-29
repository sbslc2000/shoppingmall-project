package org.cau.shoppingmall.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/*
*  "/" 을 핸들링 하기 위한 controller
* */
@Controller
@RequiredArgsConstructor
public class BasicController {

    private final HomeController homeController;

    @GetMapping("/")
    public String goHome(Model model) {
        String view = homeController.homeController(model, null);
        return view;
    }
}
