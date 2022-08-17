package org.cau.shoppingmall.service;

import lombok.RequiredArgsConstructor;
import org.cau.shoppingmall.dto.orders.OrderForm;
import org.cau.shoppingmall.dto.orders.OrderDto;
import org.cau.shoppingmall.entity.order.*;
import org.cau.shoppingmall.repository.ItemRepository;
import org.cau.shoppingmall.repository.OrderProcessRepository;
import org.cau.shoppingmall.repository.OrdersRepository;
import org.cau.shoppingmall.repository.PaymentMethodRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{


    private final ItemRepository itemRepository;
    private final OrdersRepository orderRepository;
    private final OrderProcessRepository orderProcessRepository;
    private final PaymentMethodRepository paymentMethodRepository;

    /*
        Order createOrder : 사용자에게 입력받은 정보를 토대로 주문을 등록한다.

        프로세스 로직:
         - "입금전" OrderProcess를 entity 가져오기
         - 결제 수단에 따른 PaymentMethod entity 가져오기
         - form 의 정보로 order, payment, cashReceipt Entity 생성, Order <-> User 연관관계 매핑
         - OrderedItem Entity 생성 -> Order 과 연관관계 매핑

         체크사항
         - stockDetails에 재고가 있는지
         - 서버로 들어온 사용 포인트가 user의 포인트보다 작은지

         db 업데이트 사항
         - user->shoppingmallData에서 구매 횟수 1 증가
         - user->shoppingmallData에서 포인트 ++
         - 각각의 item의 누적 판매량 증가
         - 각각의 item의 전체 재고량 감소
         - item -> stockDetails의 재고량 감소
 */
    @Override
    public Orders create(OrderForm form, Long userId) {
        OrderProcess orderProcess = orderProcessRepository.findById(1L).get();
        PaymentMethod paymentMethod = paymentMethodRepository.findById(form.getPaymentMethod()).get();


        CashReceipt cashReceipt = form.isCashReceiptFlag() ? form.createCashReceipt() : null;

        int price;
        //user 의 point를 확인한 후 sum of orderedItem.price - point 한 가격 주입하기기


       form.createPayment(paymentMethod,price,cashReceipt);

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
