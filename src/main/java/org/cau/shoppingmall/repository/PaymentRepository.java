package org.cau.shoppingmall.repository;

import org.cau.shoppingmall.entity.order.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
