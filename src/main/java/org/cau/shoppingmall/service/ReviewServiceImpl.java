package org.cau.shoppingmall.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cau.shoppingmall.dto.review.ReviewDto;
import org.cau.shoppingmall.dto.review.ReviewForm;
import org.cau.shoppingmall.entity.item.Color;
import org.cau.shoppingmall.entity.item.Item;
import org.cau.shoppingmall.entity.item.OrderedItem;
import org.cau.shoppingmall.entity.item.Size;
import org.cau.shoppingmall.entity.order.Orders;
import org.cau.shoppingmall.entity.user.Review;
import org.cau.shoppingmall.entity.user.User;
import org.cau.shoppingmall.repository.item.ColorRepository;
import org.cau.shoppingmall.repository.item.ItemRepository;
import org.cau.shoppingmall.repository.item.SizeRepository;
import org.cau.shoppingmall.repository.order.OrdersRepository;
import org.cau.shoppingmall.repository.ReviewRepository;
import org.cau.shoppingmall.repository.UserRepository;
import org.cau.shoppingmall.util.ColorConverter;
import org.cau.shoppingmall.util.SizeConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final ItemRepository itemRepository;
    private final OrdersRepository ordersRepository;
    private final ImageService imageService;

    private final ColorRepository colorRepository;
    private final SizeRepository sizeRepository;

    @Override
    @Transactional
    public Long create(ReviewForm form, Long userId, List<MultipartFile> fileList) throws IOException {


        List<String> imgList = imageService.storeReviewImages(fileList);

        User user = userRepository.findById(userId).get();
        Item item = itemRepository.findById(form.getItemId()).orElseThrow(
                () -> new NoSuchElementException("해당하는 상품이 없습니다.")
        );

        //입력받은 상품이 사용자가 구매한 상품인지 확인
        List<Orders> orderList = ordersRepository.findAllByUser_Id(user.getId());

        boolean find = false;
        for(Orders order : orderList) {
            if(find == true) break;

            List<OrderedItem> orderedItemList = order.getOrderedItemList();

            for(OrderedItem orderedItem : orderedItemList) {
                if(orderedItem.getColor().getId().equals(form.getItemDetails().getColorId())
                        && orderedItem.getSize().getId().equals(form.getItemDetails().getSizeId())
                        && orderedItem.getItem().getId().equals(form.getItemId())
                && !orderedItem.isReviewFlag()) {
                    log.info("find unreviewed orderedItem");
                    orderedItem.setTrueAtReviewFlag();
                    find = true;
                }
            }
        }

        if(find == false) {
            throw new NoSuchElementException("사용자가 구매한 상품이 아니거나 이미 작성된 리뷰입니다.");
        }


        Color color = colorRepository.findById(form.getItemDetails().getColorId()).get();

        Size size = sizeRepository.findById(form.getItemDetails().getSizeId()).get();

        Review buildedReview = form.toEntity(user, item, imgList.toString(),color,size);
        Review savedReview = reviewRepository.save(buildedReview);

        item.addNewReviewStars(savedReview.getStars());
        user.getShoppingData().addReviews();
        
        //orderedItem 의 reviewFlag 를 true로 바꿔야하는데.. 어떻게 하지




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

    @Override
    public List<ReviewDto> getAllReviews(Long itemId) {

        Item item = itemRepository.findById(itemId).orElseThrow(
                () -> new NoSuchElementException("해당하는 상품이 없습니다.")
        );

        List<Review> reviewList = reviewRepository.findAllByItem(item);

        List<ReviewDto> result = reviewList.stream().map(m -> ReviewDto.of(m)).collect(Collectors.toList());

        result.sort(Comparator.comparing(ReviewDto::getId).reversed());

        return result;
    }
}
