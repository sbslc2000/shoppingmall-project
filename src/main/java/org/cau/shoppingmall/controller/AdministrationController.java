package org.cau.shoppingmall.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cau.shoppingmall.dto.orders.OrderDto;
import org.cau.shoppingmall.exception.NoAuthInfoFoundException;
import org.cau.shoppingmall.service.LoginService;
import org.cau.shoppingmall.service.OrderService;
import org.cau.shoppingmall.user.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/mng")
public class AdministrationController {

    private final LoginService loginService;
    private final OrderService orderService;


    @GetMapping("/orders")
    public String checkOrders(HttpSession session, Model model) {

        UserDetails userDetails = getUserDetails(session);

        if(userDetails.getAuthority().getId().equals(1)) {
            throw new RuntimeException("관리자 권한이 아닙니다.");
        } else {
            List<OrderDto> orderList = orderService.getAllOrder();

            model.addAttribute("orderList",orderList);
        }



        return "mng/orders";
    }

    private UserDetails getUserDetails(HttpSession session) throws RuntimeException{
        try {
            UserDetails userDetails = loginService.getLoginedUserData(session);
            return userDetails;
        } catch (NoAuthInfoFoundException e) {
            throw new RuntimeException(e);
        }
    }


}
