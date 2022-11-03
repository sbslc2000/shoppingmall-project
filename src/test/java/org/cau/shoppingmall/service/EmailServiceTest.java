package org.cau.shoppingmall.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.io.IOException;

@SpringBootTest
@Transactional
class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Test
    void emailService()  throws MessagingException, IOException {
        emailService.sendWelcomeEmail("dayeon620@kakao.com", "dayeon662052@gmail.com");
    }

}