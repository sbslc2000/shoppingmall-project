package org.cau.shoppingmall.repository;

import org.cau.shoppingmall.entity.order.OrderProcess;
import org.cau.shoppingmall.repository.order.OrderProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;

//@SpringBootTest
class OrderProcessRepositoryTest {

    @Autowired
    private OrderProcessRepository orderProcessRepository;
    //@Test
    void insert() {
        OrderProcess op1 = new OrderProcess().builder()
                .name("입금 전")
                .build();
        OrderProcess op2 = new OrderProcess().builder()
                .name("배송 준비중")
                .build();
        OrderProcess op3 = new OrderProcess().builder()
                .name("배송 준비중")
                .build();
        OrderProcess op4 = new OrderProcess().builder()
                .name("배송 완료")
                .build();

        orderProcessRepository.save(op1);
        orderProcessRepository.save(op2);
        orderProcessRepository.save(op3);
        orderProcessRepository.save(op4);

    }

}