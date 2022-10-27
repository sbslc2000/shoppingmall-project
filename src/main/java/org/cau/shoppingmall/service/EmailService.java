package org.cau.shoppingmall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public interface EmailService {
    void sendMail(String mailTo,String from, String text);


    void sendWelcomeEmail(String mailTo, String from);
}
