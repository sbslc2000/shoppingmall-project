package org.cau.shoppingmall.repository;

import org.cau.shoppingmall.entity.item.OrderedItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderedItemRepository extends JpaRepository<OrderedItem,Long> {
}
