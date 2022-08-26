package org.cau.shoppingmall.repository;

import org.cau.shoppingmall.entity.order.PaymentMethod;
import org.cau.shoppingmall.repository.order.PaymentMethodRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class PaymentMethodRepositoryTest {

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

   // @Test
    void insert() {
        PaymentMethod pm1 = new PaymentMethod().builder()
                .name("무통장")
                .build();

        PaymentMethod pm2 = new PaymentMethod().builder()
                .name("신용카드")
                .build();

        PaymentMethod pm3 = new PaymentMethod().builder()
                .name("카카오페이")
                .build();

        paymentMethodRepository.save(pm1);
        paymentMethodRepository.save(pm2);
        paymentMethodRepository.save(pm3);


    }

}