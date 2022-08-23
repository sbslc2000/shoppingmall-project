package org.cau.shoppingmall.service;

import lombok.RequiredArgsConstructor;
import org.cau.shoppingmall.dto.review.ReviewDto;
import org.cau.shoppingmall.dto.review.ReviewForm;
import org.cau.shoppingmall.entity.item.Item;
import org.cau.shoppingmall.entity.item.OrderedItem;
import org.cau.shoppingmall.entity.order.Orders;
import org.cau.shoppingmall.entity.user.Review;
import org.cau.shoppingmall.entity.user.User;
import org.cau.shoppingmall.repository.ItemRepository;
import org.cau.shoppingmall.repository.OrdersRepository;
import org.cau.shoppingmall.repository.ReviewRepository;
import org.cau.shoppingmall.repository.UserRepository;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final ItemRepository itemRepository;
    private final OrdersRepository ordersRepository;
    @Override
    public Long create(ReviewForm form, Long userId,String img) {

        User user = userRepository.findById(userId).get();
        Item item = itemRepository.findById(form.getItemId()).orElseThrow(
                () -> new NoSuchElementException("해당하는 상품이 없습니다.")
        );


        Review buildedReview = form.toEntity(user, item, img);
        Review savedReview = reviewRepository.save(buildedReview);

        item.addNewReviewStars(savedReview.getStars());
        user.getShoppingData().addReviews();
        
        //orderedItem 의 reviewFlag 를 true로 바꿔야하는데.. 어떻게 하지

        List<Orders> orderList = ordersRepository.findAllByUser_Id(user.getId());

        boolean find = false;
        for(Orders order : orderList) {
            if(find == true) break;

            List<OrderedItem> orderedItemList = order.getOrderedItemList();

            for(OrderedItem orderedItem : orderedItemList) {
                if(orderedItem.getColor().getId() == savedReview.getItemDetails().getColorId()
                && orderedItem.getSize().getId() == savedReview.getItemDetails().getSizeId()
                && orderedItem.getItem().getId() == savedReview.getItem().getId()) {
                    orderedItem.setTrueAtReviewFlag();
                    find = true;
                }
            }
        }

        return savedReview.getId();
    }

    @Override
    public ReviewDto get(Long reviewId) {

        Review findReview = reviewRepository.findById(reviewId).orElseThrow(
                () -> new NoSuchElementException("해당 리뷰가 존재하지 않습니다.")
        );


        ReviewDto result = ReviewDto.of(findReview);

        return result;
    }

    @Override
    public List<ReviewDto> getRecent2ReviewsByItemId(Long itemId) {
        return null;
    }

    @Override
    public void delete(Long reviewId, Long userId) {

    }
}
