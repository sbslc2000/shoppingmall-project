package org.cau.shoppingmall.util;

import org.cau.shoppingmall.dto.item.ItemDetails;
import org.cau.shoppingmall.dto.review.ReviewForm;
import org.cau.shoppingmall.entity.item.OrderedItem;
import org.springframework.stereotype.Service;

@Service
public class ReviewUtil {


    public ReviewForm createReviewForm(OrderedItem orderedItem) {

        ItemDetails itemDetails = new ItemDetails();
        itemDetails.setColorId(orderedItem.getColor().getId());
        itemDetails.setSizeId(orderedItem.getSize().getId());

        ReviewForm reviewForm = new ReviewForm().builder()
                .itemId(orderedItem.getItem().getId())
                .content("옷이 정말 좋아요 ㅎㅎ")
                .itemDetails(itemDetails)
                .stars(5)
                .title("리뷰 제목입니다!")
                .build();

        return reviewForm;
    }
}
