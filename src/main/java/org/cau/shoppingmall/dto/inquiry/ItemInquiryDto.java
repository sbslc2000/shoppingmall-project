package org.cau.shoppingmall.dto.inquiry;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cau.shoppingmall.dto.Users.UserDto;
import org.cau.shoppingmall.dto.item.ItemDto;
import org.cau.shoppingmall.entity.inquiry.ItemInquiry;
import org.cau.shoppingmall.entity.inquiry.OneToOneInquiry;
import org.cau.shoppingmall.util.ListUtil;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemInquiryDto {

    private Long id;

    private ItemDto item;

    private UserDto user;



    private String title;




    private LocalDateTime registerDate;

    private List<String> img;

    private String content;

    public static ItemInquiryDto of(ItemInquiry inquiry) {

        return new ItemInquiryDto().builder()
                .id(inquiry.getId())
                .item(ItemDto.of(inquiry.getItem()))
                .user(UserDto.of(inquiry.getUser()))
                .title(inquiry.getTitle())
                .registerDate(inquiry.getRegisterDate())
                .img(ListUtil.parse(inquiry.getImg()))
                .content(inquiry.getContent())
                .build();
    }

}
