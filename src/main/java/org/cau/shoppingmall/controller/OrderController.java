package org.cau.shoppingmall.controller;

import lombok.RequiredArgsConstructor;
import org.cau.shoppingmall.dto.orders.OrderDto;
import org.cau.shoppingmall.dto.orders.OrderForm;
import org.cau.shoppingmall.dto.orders.OrderItem;
import org.cau.shoppingmall.entity.order.Orders;
import org.cau.shoppingmall.service.OrderService;
import org.springframework.beans.factory.annotation.Required;import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @PostMapping("/order-form")
    public String showOrderForm(Model model,
                            @ModelAttribute(value = "orderItem") OrderItem orderItemList) {

        for (OrderItem orderItem : orderItemList.getOrderItemList()) {
            System.out.println(orderItem.getColorId());
            orderService.setNameData(orderItem);
        }

        model.addAttribute("orderItems",orderItemList.getOrderItemList());
        return "order/order_form";

    }


    @PostMapping("/order")
    public String createOrder(RedirectAttributes redirect,
                              @ModelAttribute OrderForm orderForm) {

        Long userId = 0L;
        //userId를 세션에서 가져옴
        OrderDto order = orderService.create(orderForm, userId);


    }



}
