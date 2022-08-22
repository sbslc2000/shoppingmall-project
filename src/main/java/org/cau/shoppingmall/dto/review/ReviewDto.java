package org.cau.shoppingmall.dto.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cau.shoppingmall.dto.item.ItemDto;
import org.cau.shoppingmall.entity.item.Item;
import org.cau.shoppingmall.entity.user.Review;
import org.cau.shoppingmall.util.ListUtil;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {

    private ItemDto item;

    private String username;

    private LocalDateTime registerDate;

    private int stars;

    private String title;

    private List<String> img;

    private String content;


    public static ReviewDto of(Review review) {

        List<String> parsedImg = ListUtil.parse(review.getImg());
        ItemDto itemDto = ItemDto.of(review.getItem());
        return new ReviewDto().builder()
                .item(itemDto)
                .username(review.getUser().getUserName())
                .registerDate(LocalDateTime.now())
                .stars(review.getStars())
                .title(review.getTitle())
                .img(parsedImg)
                .content(review.getContent())
                .build();
    }

}
