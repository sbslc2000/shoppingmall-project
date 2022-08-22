package org.cau.shoppingmall.service;

import org.cau.shoppingmall.dto.orders.OrderedItemForm;
import org.cau.shoppingmall.entity.item.Item;
import org.cau.shoppingmall.repository.ItemRepository;
import org.cau.shoppingmall.util.OrderUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderUtil orderUtil;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    @DisplayName("주문 접수 테스트")
    void createOrderTest() {
        List<OrderedItemForm> orderedItemFormList = new ArrayList<>();

        Item item = itemRepository.findById(1L).get();
        //stockdetails q:40, color_id = 1 , size_id = 3

        OrderedItemForm orderedItemForm1 = new OrderedItemForm();
        orderedItemForm1.setItemId(1L);
        orderedItemForm1.setColorId(3L);
        orderedItemForm1.setItemId(1L);
        orderedItemForm1.setQuantity(3);
        orderUtil.createOrderForm();
    }


}