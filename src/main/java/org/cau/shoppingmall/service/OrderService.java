package org.cau.shoppingmall.service;

import org.cau.shoppingmall.dto.orders.OrderForm;
import org.cau.shoppingmall.dto.orders.OrderDto;

import org.cau.shoppingmall.dto.orders.OrderItem;
import org.cau.shoppingmall.entity.order.Orders;

import java.util.List;


/*
 * OrderService Interface
 * */
public interface OrderService {


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
    OrderDto create(OrderForm form, Long userId);


    /*
    * OrderDto get : Id 에 해당하는 주문정보를 반환한다.
    *
    * 프로세스 로직:
    * session에서 사용자 아이디를 확인한 후,
    * - 사용자가 manager 라면 : 무조건 반환
    * - 사용자가 일반사용자라면 : Order 의 userId 와 비교해서 같은 경우만 반환
    * */
    OrderDto get(Long orderId,Long userId) throws Exception;

    void setNameData(OrderItem orderItem);


    /*
    * Administration ONLY!!!
    * */
    List<OrderDto> getAllOrder();
}
