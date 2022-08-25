package org.cau.shoppingmall.repository.order;

import org.cau.shoppingmall.entity.order.OrderProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProcessRepository extends JpaRepository<OrderProcess,Long> {
}
