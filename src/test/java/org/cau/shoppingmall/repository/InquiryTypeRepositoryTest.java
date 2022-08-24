package org.cau.shoppingmall.repository;

import org.cau.shoppingmall.entity.inquiry.InquiryType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
class InquiryTypeRepositoryTest {

    @Autowired
    private InquiryTypeRepository inquiryTypeRepository;

    //@Test
    void insert() {
        InquiryType type1 = new InquiryType().builder()
                .name("배송")
                .build();
        InquiryType type2 = new InquiryType().builder()
                .name("엣지")
                .build();

        InquiryType type3 = new InquiryType().builder()
                .name("주문/결제")
                .build();
        InquiryType type4 = new InquiryType().builder()
                .name("교환/반품")
                .build();
        InquiryType type5 = new InquiryType().builder()
                .name("회원")
                .build();
        InquiryType type6 = new InquiryType().builder()
                .name("기타")
                .build();


        inquiryTypeRepository.save(type1);
        inquiryTypeRepository.save(type2);
        inquiryTypeRepository.save(type3);
        inquiryTypeRepository.save(type4);
        inquiryTypeRepository.save(type5);
        inquiryTypeRepository.save(type6);


    }

}