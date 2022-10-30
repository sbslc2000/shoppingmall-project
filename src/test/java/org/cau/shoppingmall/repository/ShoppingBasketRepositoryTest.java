package org.cau.shoppingmall.repository;

import org.cau.shoppingmall.entity.item.Item;
import org.cau.shoppingmall.entity.user.ShoppingBasket;
import org.cau.shoppingmall.entity.user.User;
import org.cau.shoppingmall.repository.item.ItemRepository;
import org.cau.shoppingmall.util.UserUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
//@Transactional
class ShoppingBasketRepositoryTest {

    @Autowired
    private ShoppingBasketRepository shoppingBasketRepository;
    @Autowired
    private UserUtil userUtil;

    @Autowired
    private ItemRepository itemRepository;
    @Test
    @DisplayName("jpa 메서드를 어떻게 만들어야 할까요")
    void test1() {


        User user = userUtil.createUser();
        Item item = itemRepository.findById(1L).get();

        ShoppingBasket buildedShoppingBasket = new ShoppingBasket().builder()
                .item(item)
                .user(user).build();
        ShoppingBasket savedSB = shoppingBasketRepository.save(buildedShoppingBasket);


        List<ShoppingBasket> list = shoppingBasketRepository.findByUserUserId(user.getUserId());
        List<ShoppingBasket> list2 = shoppingBasketRepository.findByUserId(user.getId());


        list.stream().forEach(m -> System.out.println(m.getId()+" "+m.getUser().getUserId()+" "+m.getItem().getName()));
        list2.stream().forEach(m -> System.out.println(m.getId()+" "+m.getUser().getUserId()+" "+m.getItem().getName()));
    }

}