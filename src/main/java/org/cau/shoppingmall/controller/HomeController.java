package org.cau.shoppingmall.controller;

import lombok.RequiredArgsConstructor;
import org.cau.shoppingmall.dto.item.ItemDto;
import org.cau.shoppingmall.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class HomeController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final ItemService itemService;

    @GetMapping
    public String homeController(Model model) {
        log.info("someone accessed!");
        List<ItemDto> hotItems = itemService.getHot4Items();

        model.addAttribute("hotItems",hotItems);
        return "home/home";

    }


}
