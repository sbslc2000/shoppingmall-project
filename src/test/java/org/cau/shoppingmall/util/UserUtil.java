package org.cau.shoppingmall.util;

import org.cau.shoppingmall.entity.user.AccountData;
import org.cau.shoppingmall.entity.user.ShoppingData;
import org.cau.shoppingmall.entity.user.User;
import org.cau.shoppingmall.repository.AccountDataRepository;
import org.cau.shoppingmall.repository.AuthorityRepository;
import org.cau.shoppingmall.repository.ShoppingDataRepository;
import org.cau.shoppingmall.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserUtil {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private AccountDataRepository accountDataRepository;

    @Autowired
    private ShoppingDataRepository shoppingDataRepository;

    @Autowired
    private UserRepository userRepository;

    public User createUser() {

        AccountData buildedAccountData = new AccountData().builder()
                .build();

        AccountData savedAccountData = accountDataRepository.save(buildedAccountData);

        ShoppingData buildShoppingData = new ShoppingData().builder()
                .baskets(0)
                .count(0)
                .likes(0)
                .reviews(0)
                .baskets(0)
                .build();

        ShoppingData savedShoppingData = shoppingDataRepository.save(buildShoppingData);


        User user = new User().builder()
                .userId("sbslc2000")
                .authority(authorityRepository.findById(2L).get())
                .accountData(savedAccountData)
                .shoppingData(savedShoppingData)
                .build();

        User savedUser = userRepository.save(user);

        return savedUser;
    }
}
