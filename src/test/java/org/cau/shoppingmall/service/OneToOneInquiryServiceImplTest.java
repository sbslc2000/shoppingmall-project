package org.cau.shoppingmall.service;

import org.assertj.core.api.Assertions;
import org.cau.shoppingmall.dto.inquiry.OneToOneInquiryDto;
import org.cau.shoppingmall.dto.inquiry.OneToOneInquiryForm;
import org.cau.shoppingmall.entity.inquiry.OneToOneInquiry;
import org.cau.shoppingmall.entity.user.User;
import org.cau.shoppingmall.util.UserUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OneToOneInquiryServiceImplTest {

    @Autowired
    private OneToOneInquiryService inquiryService;

    @Autowired
    private UserUtil userUtil;

    @Test
    @DisplayName("1대1 질문 정상 등록 확인")
    void createInquiry() throws IOException {
        User user = userUtil.createUser();
        Long userId = user.getId();
        OneToOneInquiryForm form = new OneToOneInquiryForm();
        form.setContent("1대1 질문 내용입니다.");
        form.setType(1L);

        List<MultipartFile> fileList = new ArrayList<>();
        OneToOneInquiry oneToOneInquiry = inquiryService.create(form, userId, fileList);


        //

        List<OneToOneInquiryDto> inquiryList = inquiryService.getByUserId(userId);

        assertThat(inquiryList.size()).isEqualTo(1);
        assertThat(inquiryList.get(0).getId()).isEqualTo(oneToOneInquiry.getId());
    }


}