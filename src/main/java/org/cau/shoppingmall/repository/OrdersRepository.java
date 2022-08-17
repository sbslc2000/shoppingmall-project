package org.cau.shoppingmall.repository;

import org.cau.shoppingmall.entity.order.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders,Long> {
}
