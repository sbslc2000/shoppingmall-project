package org.cau.shoppingmall.controller;

import lombok.RequiredArgsConstructor;
import org.cau.shoppingmall.dto.Users.LoginForm;
import org.cau.shoppingmall.dto.orders.OrderDto;
import org.cau.shoppingmall.dto.orders.OrderForm;
import org.cau.shoppingmall.dto.orders.OrderItem;
import org.cau.shoppingmall.exception.notfound.NoAuthInfoFoundException;
import org.cau.shoppingmall.service.LoginService;
import org.cau.shoppingmall.service.OrderService;
import org.cau.shoppingmall.user.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final LoginService loginService;


    @PostMapping("/order-form")
    public String showOrderForm(Model model,
                                @ModelAttribute(value = "orderItem") OrderItem orderItemList,
                                HttpSession session, RedirectAttributes redirect) {

        try {
            UserDetails userDetails = loginService.getLoginedUserData(session);
        } catch (NoAuthInfoFoundException e) {
            List<String> errors = new ArrayList<>();
            errors.add("로그인이 필요한 서비스 입니다.");
            redirect.addFlashAttribute("errors", errors);
            redirect.addFlashAttribute("loginForm",new LoginForm(null,null));
            return "redirect:/login";

        }
        for (OrderItem orderItem : orderItemList.getOrderItemList()) {
            System.out.println(orderItem.getColorId());
            orderService.setNameData(orderItem);
        }

        model.addAttribute("orderItems", orderItemList.getOrderItemList());
        return "order/order_form";

    }


    @PostMapping("/order")
    public String createOrder(RedirectAttributes redirect,
                              @ModelAttribute OrderForm orderForm) {

        Long userId = 0L;
        //userId를 세션에서 가져옴
        OrderDto order = orderService.create(orderForm, userId);

        return "OK";
    }



}
