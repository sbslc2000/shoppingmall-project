package org.cau.shoppingmall.repository;

import org.cau.shoppingmall.entity.user.ShoppingBasket;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingBasketRepository {

    List<ShoppingBasket> findByUserId(String userId);

}
