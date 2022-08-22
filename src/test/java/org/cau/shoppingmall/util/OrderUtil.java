package org.cau.shoppingmall.util;

import org.cau.shoppingmall.dto.orders.OrderForm;
import org.cau.shoppingmall.dto.orders.OrderedItemForm;
import org.cau.shoppingmall.entity.order.Orders;
import org.cau.shoppingmall.entity.user.User;
import org.cau.shoppingmall.repository.OrderProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.time.LocalDateTime;

@Service
public class OrderUtil {

    @Autowired
    private OrderProcessRepository orderProcessRepository;

    public  OrderForm createOrderForm(List<OrderedItemForm> orderedItemFormList) {

        OrderForm orderForm = OrderForm.builder()
                .orderedItemList(orderedItemFormList)
                .ordererName("테스트용 주문자")
                .ordererContact("테스트용 주문자 연락처")
                .recipientName("테스트용 수령자")
                .recipientContact("테스트용 수령자 연락처")
                .shippingRequirement("테스트용 배송시 요구사항")
                .locationCode("15980")
                .location("서울특별시 동작구 흑석로 84")
                .locationDetail("310관 826호")
                .termsAndConditionsAccepted(true)
                .orderedItems(orderedItemFormList.size())
                .pointUsed(0)
                .paymentMethod(1L)
                .cashReceiptFlag(false)
                .build();

        return orderForm;

    }
}
