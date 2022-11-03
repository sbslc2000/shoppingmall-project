package org.cau.shoppingmall.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cau.shoppingmall.common.dto.MessageDto;
import org.cau.shoppingmall.common.handler.MessageHandler;
import org.cau.shoppingmall.dto.Users.UserDto;
import org.cau.shoppingmall.dto.inquiry.ItemInquiryDto;
import org.cau.shoppingmall.dto.inquiry.ItemInquiryForm;
import org.cau.shoppingmall.dto.item.ItemDto;
import org.cau.shoppingmall.dto.review.ReviewDto;
import org.cau.shoppingmall.entity.inquiry.ItemInquiry;
import org.cau.shoppingmall.exception.notfound.NoAuthInfoFoundException;
import org.cau.shoppingmall.service.*;
import org.cau.shoppingmall.user.UserDetails;
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
            UserDetails userDetails = loginService.getLoginedUserData(session);
            user = userDetails.getUser();
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


    //아이템 문의 작성 폼
    @GetMapping("/inquiry/{itemId}")
    public String itemAskForm(@PathVariable Long itemId,
                              Model model,HttpSession session) {

        try {
            UserDetails userDetails = loginService.getLoginedUserData(session);
            UserDto user = userDetails.getUser();
            model.addAttribute("user",user);

        } catch (NoAuthInfoFoundException e) {
            MessageDto message = new MessageDto("로그인이 필요한 서비스입니다.", "/login", RequestMethod.GET, null);
            return MessageHandler.showMessageAndRedirect(message,model);
        }


        model.addAttribute("itemInquiryForm",new ItemInquiryForm());
        model.addAttribute("itemId",itemId);

        return "item/item_ask";
    }

    //아이템 개별 문의 확인
    /*
    * todo : 아이템 개별 문의 확인 컨트롤러 작성
    * */
    @GetMapping("/inquiry/{itemId}/{inquiryId}")
    public String showItemInquiry() {
        return null;
    }

    @PostMapping("/inquiry")
    public String createItemInquiry(@ModelAttribute ItemInquiryForm form, RedirectAttributes redirect, HttpSession session) {

        try {
            UserDetails userDetails = loginService.getLoginedUserData(session);

            ItemInquiry itemInquiry = itemInquiryService.create(form, userDetails.getId());

            Long itemId = itemInquiry.getItem().getId();

            //db setting



            //redirect setting
            List<ReviewDto> reviews = reviewService.getAllReviews(itemId);
            List<ItemInquiryDto> itemInquirys = itemInquiryService.getByItemId(itemId);
            ItemDto item = itemService.get(itemId);
            redirect.addFlashAttribute("reviews",reviews);
            redirect.addFlashAttribute("itemInquirys",itemInquirys);
            redirect.addFlashAttribute("user", userDetails.getUser());
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
