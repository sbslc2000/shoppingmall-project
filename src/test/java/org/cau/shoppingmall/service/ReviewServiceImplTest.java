package org.cau.shoppingmall.service;

import org.assertj.core.api.Assertions;
import org.cau.shoppingmall.dto.orders.OrderForm;
import org.cau.shoppingmall.dto.orders.OrderedItemForm;
import org.cau.shoppingmall.dto.review.ReviewDto;
import org.cau.shoppingmall.entity.item.Item;
import org.cau.shoppingmall.entity.user.Review;
import org.cau.shoppingmall.entity.user.User;
import org.cau.shoppingmall.repository.ReviewRepository;
import org.cau.shoppingmall.repository.item.ItemRepository;
import org.cau.shoppingmall.util.OrderUtil;
import org.cau.shoppingmall.util.ReviewUtil;
import org.cau.shoppingmall.util.UserUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.NoSuchElementException;

@SpringBootTest
@Transactional
class ReviewServiceImplTest {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewUtil reviewUtil;

    @Autowired
    private UserUtil userUtil;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderUtil orderUtil;

    @Autowired
    private OrderService orderService;

    @Test
    @DisplayName("리뷰 생성 테스트")
    void createReview() throws IOException {
        Long testItemId = 1L;
        Item item = itemRepository.findById(testItemId).get();
        List<Review> beforeReviews = reviewRepository.findAllByItem(item);
        int beforeReviewsCount = beforeReviews.size();

        //유저생성
        User user = userUtil.createUser();

        //주문 생성에 필요한 아이템 정보 생성
        List<OrderedItemForm> orderedItemFormList = new ArrayList<>();
        OrderedItemForm orderedItemForm = orderUtil.createOrderedItemForm(testItemId, 1L, 3L, 3);
        orderedItemFormList.add(orderedItemForm);

        //주문 생성에 필요한 주문 정보 생성
        OrderForm orderForm = orderUtil.createOrderForm(orderedItemFormList);

        //주문 생성
        orderService.create(orderForm,user.getId());

        //이미지 대체 파일
        List<MultipartFile> fileList = new ArrayList<>();


        //제작한 주문정보를 토대로 리뷰 생성
        Long reviewId = reviewService.create(reviewUtil.createReviewForm(), user.getId(),fileList);

        ReviewDto reviewDto = reviewService.get(reviewId);
        //
        List<Review> afterReviews = reviewRepository.findAllByItem(item);
        int afterReviewsCount = afterReviews.size();


        assertThat(reviewDto.getStars()).isEqualTo(5);
        assertThat(user.getShoppingData().getReviews()).isEqualTo(1);
        assertThat(item.getReviews()).isEqualTo(1);
        assertThat(afterReviewsCount - beforeReviewsCount).isEqualTo(1);
    }

    @Test
    @DisplayName("리뷰 작성 후 재작성 방지")
    void createDuplicatedReview() throws IOException {
        Long testItemId = 1L;
        Item item = itemRepository.findById(testItemId).get();
        List<Review> beforeReviews = reviewRepository.findAllByItem(item);
        int beforeReviewsCount = beforeReviews.size();

        //유저생성
        User user = userUtil.createUser();

        //주문 생성에 필요한 아이템 정보 생성
        List<OrderedItemForm> orderedItemFormList = new ArrayList<>();
        OrderedItemForm orderedItemForm = orderUtil.createOrderedItemForm(testItemId, 1L, 3L, 3);
        orderedItemFormList.add(orderedItemForm);

        //주문 생성에 필요한 주문 정보 생성
        OrderForm orderForm = orderUtil.createOrderForm(orderedItemFormList);

        //주문 생성
        orderService.create(orderForm,user.getId());

        //이미지 대체 파일
        List<MultipartFile> fileList = new ArrayList<>();


        //제작한 주문정보를 토대로 리뷰 생성
        Long reviewId = reviewService.create(reviewUtil.createReviewForm(), user.getId(),fileList);

        NoSuchElementException thrown = assertThrows(NoSuchElementException.class,
                () -> reviewService.create(reviewUtil.createReviewForm(), user.getId(),fileList));


        assertThat(thrown.getMessage()).isEqualTo("사용자가 구매한 상품이 아니거나 이미 작성된 리뷰입니다.");
    }


    @Test
    @DisplayName("특정 상품의 전체 리뷰 불러오기")
    void getReviewsTest() {
        Long testItemId = 1L;
        Item item = itemRepository.findById(testItemId).get();

        //유저생성
        User user = userUtil.createUser();

        //주문 생성에 필요한 아이템 정보 생성
        List<OrderedItemForm> orderedItemFormList = new ArrayList<>();
        OrderedItemForm orderedItemForm = orderUtil.createOrderedItemForm(testItemId, 1L, 3L, 3);
        orderedItemFormList.add(orderedItemForm);

        //주문 생성에 필요한 주문 정보 생성
        OrderForm orderForm = orderUtil.createOrderForm(orderedItemFormList);

        //주문 생성
        orderService.create(orderForm,user.getId());

        //이미지 대체 파일
        List<MultipartFile> fileList = new ArrayList<>();


        //제작한 주문정보를 토대로 리뷰 생성
        Long reviewId = null;
        try {
            reviewId = reviewService.create(reviewUtil.createReviewForm(), user.getId(),fileList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ReviewDto reviewDto = reviewService.get(reviewId);



        //then

        List<ReviewDto> reviews = reviewService.getAllReviews(testItemId);

        assertThat(reviews.size()).isEqualTo(1);


    }

    private void beforeSetting( ){

    }



}