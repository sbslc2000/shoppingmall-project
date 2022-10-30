package org.cau.shoppingmall.dto.inquiry;

import lombok.Data;
import org.cau.shoppingmall.entity.inquiry.InquiryType;
import org.cau.shoppingmall.entity.inquiry.OneToOneInquiry;
import org.cau.shoppingmall.entity.user.User;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class OneToOneInquiryForm {


    private Long type;

    private String typeDetail;

    @Size(min = 0, max = 2000 ,message="문의 내용은 2000자 이하로 작성해야 합니다.")
    private String content;

    public OneToOneInquiry toEntity(User user,InquiryType type,String img) {
        return new OneToOneInquiry().builder()
                .type(type)
                .typeDetail(typeDetail)
                .registerDate(LocalDateTime.now())
                .user(user)
                .content(content)
                .img(img)
                .build();

    }
}
