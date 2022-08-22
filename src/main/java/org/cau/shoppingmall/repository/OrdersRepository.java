package org.cau.shoppingmall.repository;

import org.cau.shoppingmall.entity.order.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders,Long> {

    List<Orders> findAllByUser_Id(Long userId);
}
