package org.cau.shoppingmall.util;

import org.cau.shoppingmall.dto.item.ItemDetails;
import org.cau.shoppingmall.dto.review.ReviewForm;
import org.springframework.stereotype.Service;

@Service
public class ReviewUtil {


    public ReviewForm createReviewForm() {

        ItemDetails itemDetails = new ItemDetails();
        itemDetails.setColorId(1L);
        itemDetails.setSizeId(3L);

        ReviewForm reviewForm = new ReviewForm().builder()
                .itemId(1L)
                .content("옷이 정말 좋아요 ㅎㅎ")
                .itemDetails(itemDetails)
                .stars(5)
                .title("리뷰 제목입니다!")
                .build();

        return reviewForm;
    }
}
