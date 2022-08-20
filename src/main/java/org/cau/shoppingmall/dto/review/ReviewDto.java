package org.cau.shoppingmall.dto.review;

import lombok.Builder;
import lombok.Data;
import org.cau.shoppingmall.dto.item.ItemDto;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class ReviewDto {

    private ItemDto item;

    private String username;

    private LocalDateTime registerDate;

    private int stars;

    private String title;

    private List<String> img;

    private String content;

}
