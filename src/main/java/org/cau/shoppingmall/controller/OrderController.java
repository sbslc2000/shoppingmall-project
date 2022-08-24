package org.cau.shoppingmall.controller;

import lombok.RequiredArgsConstructor;
import org.cau.shoppingmall.dto.orders.OrderItem;
import org.cau.shoppingmall.service.OrderService;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @GetMapping
    public String orderForm(Model model,
                            @RequestParam List<OrderItem> orderItemList) {

        for(OrderItem orderItem: orderItemList) {
            orderService.setNameData(orderItem);
        }

        model.addAttribute("orderItems",orderItemList);

        return "order/order-form";



    }


}
