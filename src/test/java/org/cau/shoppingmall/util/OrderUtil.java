package org.cau.shoppingmall.util;

import org.cau.shoppingmall.dto.orders.OrderForm;
import org.cau.shoppingmall.dto.orders.OrderedItemForm;
import org.cau.shoppingmall.repository.order.OrderProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public OrderForm createOrderForm(List<OrderedItemForm> orderedItemFormList,int pointUsed) {
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
                .pointUsed(pointUsed)
                .paymentMethod(1L)
                .cashReceiptFlag(false)
                .build();

        return orderForm;
    }

    public OrderedItemForm createOrderedItemForm(Long itemId, Long colorId, Long sizeId, int quantity) {
        OrderedItemForm orderedItemForm = new OrderedItemForm();
        orderedItemForm.setItemId(itemId);
        orderedItemForm.setColorId(colorId);
        orderedItemForm.setSizeId(sizeId);
        orderedItemForm.setQuantity(quantity);

        return orderedItemForm;
    }

    /*
    * itemId = 1, quantity = 3, size=free, color = white
    * */
    public OrderForm createOrderFormSet1() {
        List<OrderedItemForm> orderedItemFormList = new ArrayList<>();
        OrderedItemForm orderedItemForm = createOrderedItemForm(1L, 1L, 3L, 3);

        orderedItemFormList.add(orderedItemForm);

        OrderForm orderForm = createOrderForm(orderedItemFormList);

        return orderForm;
    }

    /*
    * itemId = 2, quantity = 5, size = free, color = 2
    * itemId = 11 ,quantity = 20, size= free ,color = 7
    * */
    public OrderForm createOrderFormSet2() {
        List<OrderedItemForm> orderedItemFormList = new ArrayList<>();
        OrderedItemForm orderedItemForm1 = createOrderedItemForm(2L, 2L, 3L, 5);
        OrderedItemForm orderedItemForm2 = createOrderedItemForm(11L, 7L, 3L, 20);
        orderedItemFormList.add(orderedItemForm1);
        orderedItemFormList.add(orderedItemForm2);

        OrderForm orderForm = createOrderForm(orderedItemFormList);

        return orderForm;
    }
}
