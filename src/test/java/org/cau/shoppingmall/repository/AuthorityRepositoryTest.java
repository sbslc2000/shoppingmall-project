package org.cau.shoppingmall.repository;

import org.cau.shoppingmall.entity.user.Authority;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
class AuthorityRepositoryTest {

    @Autowired
    private AuthorityRepository authorityRepository;
    //@Test
    public void insert() {

        /*
        Authority normalUser = new Authority().builder()
                .name("일반사용자")
                .build();

        Authority manager = new Authority().builder()
                .name("관리자")
                .build();

        Authority topManager = new Authority().builder()
                .name("최고관리자")
                .build();

         */

        authorityRepository.save(normalUser);
        authorityRepository.save(manager);
        authorityRepository.save(topManager);

    }

}