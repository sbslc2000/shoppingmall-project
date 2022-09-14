package org.cau.shoppingmall.dto.notice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cau.shoppingmall.dto.Users.UserDto;
import org.cau.shoppingmall.entity.Notice;
import org.cau.shoppingmall.entity.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoticeDto {

    private Long id;

    private String title;

    private String content;

    private LocalDateTime registerDate;

    private UserDto user;

    public static NoticeDto of(Notice notice) {
        return new NoticeDto().builder()
                .id(notice.getId())
                .title(notice.getTitle())
                .content(notice.getContent())
                .registerDate(notice.getRegisterDate())
                .user(UserDto.of(notice.getUser()))
                .build();
    }
}
