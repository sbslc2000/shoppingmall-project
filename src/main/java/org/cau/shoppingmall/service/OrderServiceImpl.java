package org.cau.shoppingmall.service;

import lombok.RequiredArgsConstructor;
import org.cau.shoppingmall.dto.OrderForm;
import org.cau.shoppingmall.dto.orders.OrderDto;
import org.cau.shoppingmall.entity.order.Orders;
import org.cau.shoppingmall.repository.OrdersRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrdersRepository orderRepository;
    @Override
    public Orders create(OrderForm form, Long userId) {
        return null;
    }

    @Override
    public OrderDto get(Long orderId,Long userId) {
        //userRepository 에서 userId를 통해 user을 가져온 후 manager 이면 진행, findOrder 와 userId가 동일하면 진행, 아니라면 exception 발생

        Optional<Orders> findOrder = orderRepository.findById(orderId);

        if(findOrder.isPresent()) {
            Orders orders = findOrder.get();

            //if not userId same: exception

            OrderDto result = OrderDto.of(orders);
            return result;
        } else {
            throw new NoSuchElementException("해당하는 주문정보가 없습니다.");
        }
    }
}
