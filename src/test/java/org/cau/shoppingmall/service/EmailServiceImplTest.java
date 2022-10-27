package org.cau.shoppingmall.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class EmailServiceImplTest {


    @Autowired
    private EmailService emailService;


    @Test
    void test1() {

        emailService.sendWelcomeEmail("dayeon620@kakao.com","sbslc2000");
    }


}