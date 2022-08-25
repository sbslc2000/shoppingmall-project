package org.cau.shoppingmall.controller;

import lombok.RequiredArgsConstructor;
import org.cau.shoppingmall.dto.orders.OrderItem;
import org.cau.shoppingmall.service.OrderService;
import org.springframework.beans.factory.annotation.Required;import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @PostMapping
    public String orderForm(Model model,
                            @ModelAttribute(value = "orderItem") OrderItem orderItemList) {


        for (OrderItem orderItem : orderItemList.getOrderItemList()) {
            System.out.println(orderItem.getColorId());
            orderService.setNameData(orderItem);
        }

        model.addAttribute("orderItems",orderItemList.getOrderItemList());

        return "order/order_form";



    }


}
