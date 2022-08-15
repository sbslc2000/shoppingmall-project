package org.cau.shoppingmall.service;

import org.cau.shoppingmall.dto.OrderForm;
import org.cau.shoppingmall.entity.order.Orders;

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
    Orders createOrder(OrderForm form, Long userId);
}
