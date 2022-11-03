package org.cau.shoppingmall.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    public void sendMail(String mailTo, String from, String text){

        // 수신 대상을 담을 ArrayList 생성
        ArrayList<String> toUserList = new ArrayList<>();

        // 수신 대상 추가
        toUserList.add(mailTo);

        // 수신 대상 개수
        int toUserSize = toUserList.size();

        // SimpleMailMessage (단순 텍스트 구성 메일 메시지 생성할 때 이용)
        SimpleMailMessage simpleMessage = new SimpleMailMessage();

        // 수신자 설정
        simpleMessage.setTo((String[]) toUserList.toArray(new String[toUserSize]));
        simpleMessage.setFrom(from);

        // 메일 제목
        simpleMessage.setSubject("Subject Sample");

        // 메일 내용
        simpleMessage.setText(text);

        // 메일 발송
        javaMailSender.send(simpleMessage);

    }

    @Override
    public void sendWelcomeEmail(String mailTo, String from) {
        sendMail(mailTo,from,"회원가입을 환영합니다.");
    }
}
