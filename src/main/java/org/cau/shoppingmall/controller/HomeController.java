package org.cau.shoppingmall.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cau.shoppingmall.dto.item.ItemDto;
import org.cau.shoppingmall.dto.item.ItemRequest;
import org.cau.shoppingmall.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeController {
    private final ItemService itemService;

    @GetMapping
    public String homeController(Model model) {
        log.info("someone accessed!");
        List<ItemDto> hotItems = itemService.getHot4Items();

        model.addAttribute("hotItems",hotItems);
        return "home/home";

    }

    @GetMapping("/{categoryName}")
    public String clothesController(Model model,
                                    @PathVariable String categoryName) {

        log.info("categoryName = {}",categoryName);
        Long categoryId = 0L;
        switch(categoryName) {
            case "clothes":
                categoryId = 1L; break;
            case "beauty":
                categoryId = 2L; break;
            case "bagWallet":
                categoryId = 3L; break;
            case "accessories":
                categoryId = 4L; break;
            case "shoes":
                categoryId = 5L; break;
            default:
                categoryId = 1L;
        }

        log.info("someone accessed in clothes!");

        List<ItemDto> hotItems = itemService.getHot8Items(categoryId);

        ItemRequest itemRequest = new ItemRequest();
        List<Long> category = new ArrayList<>();
        category.add(categoryId);
        itemRequest.setCategory(category);
        Page<ItemDto> itempage = itemService.getItemsByConditions(itemRequest);
        List<ItemDto> items = itempage.getContent();
        log.info("items.size()={}",items.size());
        model.addAttribute("items",items);
        model.addAttribute("hotItems",hotItems);



        return "home/cloths";

    }



}
