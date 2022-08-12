package org.cau.shoppingmall.repository;

import org.cau.shoppingmall.entity.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ItemRepository extends JpaRepository<Item,Long>, JpaSpecificationExecutor<Item> {
}
