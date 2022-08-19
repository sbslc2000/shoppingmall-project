package org.cau.shoppingmall.controller;

import lombok.RequiredArgsConstructor;
import org.cau.shoppingmall.dto.item.ItemDto;
import org.cau.shoppingmall.dto.item.ItemRequest;
import org.cau.shoppingmall.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
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

    @GetMapping("/clothes")
    public String clothesController(Model model) {

        Long categoryId = 1L;
        log.info("someone accessed in clothes!");
        duplicated(model, categoryId);


        return "home/cloths";

    }

    @GetMapping("/beauty")
    public String beautyController(Model model) {
        log.info("someone accessed in beauty!");
        Long categoryId = 2L;
        duplicated(model, categoryId);


        return "home/beauty";

    }



    @GetMapping("/bagWallet")
    public String bagWalletController(Model model) {
        log.info("someone accessed in bagWallet!");
        Long categoryId = 3L;
        duplicated(model, categoryId);


        return "home/bagWallet";

    }

    @GetMapping("/accessories")
    public String accessoriesController(Model model) {
        log.info("someone accessed in accessories!");
        Long categoryId = 4L;
        duplicated(model, categoryId);

        return "home/accessories";

    }

    @GetMapping("/shoes")
    public String shoesController(Model model) {
        log.info("someone accessed in shoes!");
        Long categoryId = 5L;
        duplicated(model, categoryId);

        return "home/shoes";

    }

    private void duplicated(Model model, Long categoryId) {
        List<ItemDto> hotItems = itemService.getHot8Items(categoryId);

        ItemRequest itemRequest = new ItemRequest();
        List<Long> category = new ArrayList<>();
        category.add(categoryId);
        itemRequest.setCategory(category);
        Page<ItemDto> itempage = itemService.getItemsByConditions(itemRequest);
        List<ItemDto> items = itempage.getContent();
        model.addAttribute("items",items);
        model.addAttribute("hotItems",hotItems);
    }


}
