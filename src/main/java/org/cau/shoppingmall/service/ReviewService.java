package org.cau.shoppingmall.service;

import org.cau.shoppingmall.dto.review.ReviewForm;
import org.cau.shoppingmall.dto.review.ReviewDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ReviewService {

    /*
    * createReview - 사용자에게 받은 정보를 바탕으로 상품에 대한 리뷰를 생성한다.
    *
    * 프로세스 로직:
    * - multipart 를 통해 사용자가 입력한 사진을 서버에 저장하고 파일명을 포함한 path를 각각 string_list 형식으로 변형한다.
    * - form을 Entity로 변환 후 db에 저장한다.
    * - item과 연관관계 매핑을 지어준다.
    * - review 의 id를 반환한다.
    *
    * 체크사항
    * - user->shoppingmallData 에 작성 리뷰수를 올린다.
    * */
    Long create(ReviewForm form, Long userId, List<MultipartFile> fileList) throws IOException;


    /*
    * get
    *
    * id에 해당하는 review를 반환한다.
    * */
    ReviewDto get(Long reviewId);

    /*
    * getRecent2ReviewsByItemId;
    *
    * 해당 상품의 가장 최신 리뷰 2개를 반환한다.
    * */
    List<ReviewDto> getRecent2ReviewsByItemId(Long itemId);

    /*
    * delete - 리뷰를 삭제한다.
    *
    * 프로세스 로직:
    * - review를 삭제한다.
    * - item과 연관관계매핑을 제거해준다.
    *
    * 체크사항
    * - userId가 review 작성자의 id와 동일한지 체크한다.
    * - 리뷰 작성 횟수를 1 내린다.
    * */
    void delete(Long reviewId, Long userId);
}
