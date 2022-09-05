package org.cau.shoppingmall.dto.inquiry;

import lombok.Data;
import org.cau.shoppingmall.entity.inquiry.InquiryType;
import org.cau.shoppingmall.entity.inquiry.ItemInquiry;
import org.cau.shoppingmall.entity.inquiry.OneToOneInquiry;
import org.cau.shoppingmall.entity.item.Item;
import org.cau.shoppingmall.entity.user.User;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class ItemInquiryForm {

    private String title;

    private Long itemId;

    @Size(min = 0, max = 2000 ,message="문의 내용은 2000자 이하로 작성해야 합니다.")
    private String content;

    public ItemInquiry toEntity(User user, Item item) {
        return new ItemInquiry().builder()
                .registerDate(LocalDateTime.now())
                .item(item)
                .title(title)
                .user(user)
                .content(content)
                .build();

    }
}
