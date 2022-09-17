package org.cau.shoppingmall.dto.notice;

import lombok.Data;
import org.cau.shoppingmall.entity.Notice;
import org.cau.shoppingmall.entity.user.User;

import java.time.LocalDateTime;

@Data
public class NoticeForm {

    private String title;
    private String content;

    public Notice toEntity(User user) {
        return new Notice().builder()
                .title(title)
                .content(content)
                .user(user)
                .registerDate(LocalDateTime.now())
                .build();
    }


}
