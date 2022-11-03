package org.cau.shoppingmall.service;

public interface EmailService {

    void sendMail(String mailTo,String from, String text);
    void sendWelcomeEmail(String mailTo, String from);
}
