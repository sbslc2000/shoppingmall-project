package org.cau.shoppingmall.repository;

import org.cau.shoppingmall.entity.user.ShoppingBasket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingBasketRepository extends JpaRepository<ShoppingBasket, Long> {

    List<ShoppingBasket> findByUserId(String userId);

}
