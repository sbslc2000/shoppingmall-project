package org.cau.shoppingmall.service;

import org.cau.shoppingmall.dto.orders.OrderDto;
import org.cau.shoppingmall.dto.orders.OrderForm;
import org.cau.shoppingmall.dto.orders.OrderedItemForm;
import org.cau.shoppingmall.entity.item.Item;
import org.cau.shoppingmall.entity.item.OrderedItem;
import org.cau.shoppingmall.entity.order.Orders;
import org.cau.shoppingmall.entity.user.User;
import org.cau.shoppingmall.repository.item.ItemRepository;
import org.cau.shoppingmall.repository.order.OrderedItemRepository;
import org.cau.shoppingmall.repository.order.OrdersRepository;
import org.cau.shoppingmall.repository.UserRepository;
import org.cau.shoppingmall.util.OrderUtil;
import org.cau.shoppingmall.util.UserUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderUtil orderUtil;

    @Autowired
    private UserUtil userUtil;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrdersRepository orderRepository;

    @Autowired
    private OrderedItemRepository orderedItemRepository;

    @Test
    @DisplayName("주문 접수 테스트")
    void createOrderTest() {
        User user = userUtil.createUser();
        List<OrderedItemForm> orderedItemFormList = new ArrayList<>();

        Item item = itemRepository.findById(1L).get();
        long beforeOrderCount = orderRepository.count();
        int beforeStockCount = item.getStockDetailsList().get(0).getQuantity();
        int beforeItemQuantity = item.getQuantity();
        //stockdetails q:40, color_id = 1 , size_id = 3


        OrderedItemForm orderedItemForm = orderUtil.createOrderedItemForm(1L, 1L, 3L, 3);
        orderedItemFormList.add(orderedItemForm);

        OrderForm orderForm = orderUtil.createOrderForm(orderedItemFormList);

        OrderDto order = orderService.create(orderForm, user.getId());


        //then
        Orders findOrder = orderRepository.findById(order.getId()).get();
        User findUser = userRepository.findById(user.getId()).get();

        long afterOrderCount = orderRepository.count();
        int afterItemQuantity = item.getQuantity();
        int afterStockCount = item.getStockDetailsList().get(0).getQuantity();
        int salesCount = 0;
        for (OrderedItem oItem : findOrder.getOrderedItemList()) {
            salesCount += oItem.getQuantity();
        }

        assertThat(findOrder.getOrderedItemList().size()).isEqualTo(1);
        assertThat(findOrder.getPayment().getPaymentPrice()).isEqualTo(item.getPrice()* orderForm.getOrderedItemList().get(0).getQuantity());
        assertThat(findOrder.getPayment().isCashReceiptFlag()).isFalse();
        assertThat(beforeStockCount - afterStockCount).isEqualTo(salesCount);
        assertThat(findUser.getPoint()).isEqualTo((int) Math.round(order.getPayment().getPaymentPrice()*0.05));
        assertThat(beforeItemQuantity - afterItemQuantity).isEqualTo(salesCount);
        assertThat(item.getSales()).isEqualTo(salesCount);
        assertThat(afterOrderCount - beforeOrderCount).isEqualTo(1);
    }


    @Test
    @DisplayName("포인트 사용 테스트")
    void pointUseTest() {
        User user = userUtil.createUser(20000);

        List<OrderedItemForm> orderedItemFormList = new ArrayList<>();

        Item item = itemRepository.findById(1L).get();
        OrderedItemForm orderedItemForm = orderUtil.createOrderedItemForm(item.getId(), 1L, 3L, 3);
        orderedItemFormList.add(orderedItemForm);

        OrderForm orderForm = orderUtil.createOrderForm(orderedItemFormList,20000);

        OrderDto createdOrder = orderService.create(orderForm, user.getId());


        Orders order = orderRepository.findById(createdOrder.getId()).get();

        assertThat(order.getPayment().getPaymentPrice()).isEqualTo(39000*3 - 20000);
        assertThat(user.getPoint()).isEqualTo(4850);

    }


}