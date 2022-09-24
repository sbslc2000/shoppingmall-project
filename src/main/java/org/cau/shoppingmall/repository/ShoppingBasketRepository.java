package org.cau.shoppingmall.repository;

import org.cau.shoppingmall.entity.user.ShoppingBasket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ShoppingBasketRepository extends JpaRepository<ShoppingBasket, Long> {

    List<ShoppingBasket> findByUserId(Long userId);
    List<ShoppingBasket> findByUserUserId(String userId);

}
