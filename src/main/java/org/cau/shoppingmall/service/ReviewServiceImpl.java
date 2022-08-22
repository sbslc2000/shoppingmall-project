package org.cau.shoppingmall.service;

import org.cau.shoppingmall.dto.review.ReviewDto;
import org.cau.shoppingmall.dto.review.ReviewForm;
import org.cau.shoppingmall.entity.user.Review;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Override
    public void create(ReviewForm form, Long userId) {

    }

    @Override
    public ReviewDto get(Long reviewId) {
        return null;
    }

    @Override
    public List<ReviewDto> getRecent2ReviewsByItemId(Long itemId) {
        return null;
    }

    @Override
    public void delete(Long reviewId, Long userId) {

    }
}
