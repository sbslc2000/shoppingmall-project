package org.cau.shoppingmall.dto.review;

import lombok.*;
import org.cau.shoppingmall.dto.item.ItemDetails;
import org.cau.shoppingmall.entity.item.Item;
import org.cau.shoppingmall.entity.user.Review;
import org.cau.shoppingmall.entity.user.User;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewForm {

    private Long itemId;

    private ItemDetails itemDetails;

    private String title;

    private String content;

    private int stars;

    public Review toEntity(User user, Item item, String img) {
        Review result = new Review().builder()
                .item(item)
                .itemDetails(itemDetails)
                .user(user)
                .registerDate(LocalDateTime.now())
                .title(title)
                .content(content)
                .stars(stars)
                .img(img)
                .build();

        return result;
    }
}
