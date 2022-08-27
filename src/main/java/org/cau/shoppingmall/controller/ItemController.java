package org.cau.shoppingmall.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cau.shoppingmall.dto.item.ItemDto;
import org.cau.shoppingmall.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.NoSuchElementException;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/{itemId}")
    public String itemPage(@PathVariable Long itemId,
                           Model model) {

        ItemDto itemDto = new ItemDto();
        try {
            itemDto = itemService.get(itemId);
        } catch(NoSuchElementException e) {
            e.printStackTrace();
        }

        model.addAttribute("item",itemDto);

        return "item/item";

    }
}
