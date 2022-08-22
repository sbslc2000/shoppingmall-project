package org.cau.shoppingmall.repository;

import org.cau.shoppingmall.entity.user.ShoppingData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ShoppingDataRepository extends JpaRepository<ShoppingData,Long> {

}