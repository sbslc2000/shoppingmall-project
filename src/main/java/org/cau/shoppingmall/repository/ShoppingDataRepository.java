package org.cau.shoppingmall.repository;

import org.cau.shoppingmall.entity.user.ShoppingData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingDataRepository extends JpaRepository<ShoppingData,Long> {
}
