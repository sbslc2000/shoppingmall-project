package org.cau.shoppingmall.restcontroller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.cau.shoppingmall.dto.review.ReviewDto;
import org.cau.shoppingmall.service.ReviewService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("api/reviews")
@Api(tags = {"리뷰 관련 API"})
@RequiredArgsConstructor
public class ReviewRestController {

    private final ReviewService reviewService;

    @GetMapping
    @ApiOperation(value = "리뷰 2개 미리보기", notes="query parameter로 넘긴 id를 가진 상품의 리뷰를 2개 반환합니다.")
    public List<ReviewDto> getReview2(
            @ApiParam(value="상품 id", example = "1")
            @RequestParam Long itemId) {


        List<ReviewDto> result = reviewService.getRecent2ReviewsByItemId(itemId);

        return result;
    }

    @GetMapping("/all")
    @ApiOperation(value = "리뷰 전체보기", notes="query parameter로 넘긴 id를 가진 상품의 리뷰를 전체 반환합니다.")
    public Object getAllReview(
            @ApiParam(value="상품 id", example = "1")
            @RequestParam Long itemId) {
        return null;
    }
}
