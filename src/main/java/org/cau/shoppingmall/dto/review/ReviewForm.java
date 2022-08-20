package org.cau.shoppingmall.dto.review;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
public class ReviewForm {

    private Long itemId;

    private String itemDetails;

    private String title;

    private String content;

    private int stars;
}
