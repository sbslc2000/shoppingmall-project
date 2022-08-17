package org.cau.shoppingmall.repository;

import org.cau.shoppingmall.entity.order.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod,Long> {
}
