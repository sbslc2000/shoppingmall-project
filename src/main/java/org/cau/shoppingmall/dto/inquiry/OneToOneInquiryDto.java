package org.cau.shoppingmall.dto.inquiry;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cau.shoppingmall.dto.Users.UserDto;
import org.cau.shoppingmall.entity.inquiry.OneToOneInquiry;
import org.cau.shoppingmall.util.ListUtil;

import java.util.List;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OneToOneInquiryDto {

    private Long id;

    private UserDto user;

    private String typeName;

    private LocalDateTime registerDate;

    private List<String> img;

    private String content;

    public static OneToOneInquiryDto of(OneToOneInquiry inquiry) {

        return new OneToOneInquiryDto().builder()
                .id(inquiry.getId())
                .user(UserDto.of(inquiry.getUser()))
                .typeName(inquiry.getType().getName())
                .registerDate(inquiry.getRegisterDate())
                .img(ListUtil.parse(inquiry.getImg()))
                .content(inquiry.getContent())
                .build();
    }
}
