package org.cau.shoppingmall.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cau.shoppingmall.dto.Users.UserDto;
import org.cau.shoppingmall.dto.inquiry.ItemInquiryDto;
import org.cau.shoppingmall.dto.inquiry.ItemInquiryForm;
import org.cau.shoppingmall.dto.item.ItemDto;
import org.cau.shoppingmall.dto.review.ReviewDto;
import org.cau.shoppingmall.entity.inquiry.ItemInquiry;
import org.cau.shoppingmall.exception.NoAuthInfoFoundException;
import org.cau.shoppingmall.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;
    private final ReviewService reviewService;
    private final ItemInquiryService itemInquiryService;
    private final LoginService loginService;
    private final UserService userService;

    @GetMapping("/{itemId}")
    public String itemPage(@PathVariable Long itemId,
                           Model model, HttpSession session) {

        UserDto user = null;
        try {
            Long userId = loginService.getUserId(session);
            user = userService.get(userId);
        } catch (NoAuthInfoFoundException e) {
        }


        ItemDto itemDto = new ItemDto();
        try {
            itemDto = itemService.get(itemId);
        } catch(NoSuchElementException e) {
            e.printStackTrace();
        }

        List<ReviewDto> reviews = reviewService.getAllReviews(itemId);
        List<ItemInquiryDto> itemInquiry = itemInquiryService.getByItemId(itemId);

        model.addAttribute("user",user);
        model.addAttribute("itemInquirys",itemInquiry);
        model.addAttribute("reviews",reviews);
        model.addAttribute("item",itemDto);

        return "item/item";
    }

    @GetMapping("/ask/{itemId}")
    public String itemAskForm(@PathVariable Long itemId,
                              Model model) {

        model.addAttribute("itemInquiryForm",new ItemInquiryForm());
        model.addAttribute("itemId",itemId);

        return "item/item_ask";
    }

    @PostMapping("/inquiry")
    public String createItemInquiry(@ModelAttribute ItemInquiryForm form, RedirectAttributes redirect, HttpSession session) {

        try {
            Long userId = loginService.getUserId(session);

            ItemInquiry itemInquiry = itemInquiryService.create(form, userId);

            Long itemId = itemInquiry.getItem().getId();

            //db setting



            //redirect setting
            List<ReviewDto> reviews = reviewService.getAllReviews(itemId);
            List<ItemInquiryDto> itemInquirys = itemInquiryService.getByItemId(itemId);
            UserDto user = userService.get(userId);
            ItemDto item = itemService.get(itemId);
            redirect.addFlashAttribute("reviews",reviews);
            redirect.addFlashAttribute("itemInquirys",itemInquirys);
            redirect.addFlashAttribute("user", user);
            redirect.addFlashAttribute("item",item);

            return "redirect:/item/"+itemInquiry.getItem().getId();

        } catch (NoAuthInfoFoundException e) {
            log.warn("로그인 정보가 없는 상품 문의 요청이 들어왔습니다.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "/home";
    }
}
