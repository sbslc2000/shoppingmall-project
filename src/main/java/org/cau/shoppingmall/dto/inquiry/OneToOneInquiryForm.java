package org.cau.shoppingmall.dto.inquiry;

import lombok.Data;
import org.cau.shoppingmall.entity.inquiry.InquiryType;
import org.cau.shoppingmall.entity.inquiry.OneToOneInquiry;
import org.cau.shoppingmall.entity.user.User;

import java.time.LocalDateTime;

@Data
public class OneToOneInquiryForm {

    private Long type;

    private String content;

    public OneToOneInquiry toEntity(User user,InquiryType type,String img) {
        return new OneToOneInquiry().builder()
                .type(type)
                .registerDate(LocalDateTime.now())
                .user(user)
                .content(content)
                .img(img)
                .build();

    }
}
