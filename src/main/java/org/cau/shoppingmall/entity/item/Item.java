package org.cau.shoppingmall.entity.item;

import lombok.Getter;
import springfox.documentation.spring.web.json.Json;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@Getter
public class Item {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /*
    * todo: seller entity와 연관관계 매핑
    * */
    private Long sellerId;

    private int price;

    /*
    * todo: StockDetails entity와 연관관계 매핑
    * */
    private Long stockDetailsId;

    /*
    * 해당 상품의 전체 재고량
    * */
    private int quantity;

    /*
    * 상품의 누적 판매량
    * */
    private int sales;

    /*
    * TODO: Category Entity와 연관관계 매핑
    * */
    private Long categoryId;

    /*
     * Json 형식으로 이미지의 주소를 담고 있음
     * */
    private String img;


    /*
    * 해당 상품에 달린 리뷰의 개수
    * */
    private int reviews;

    /*
    * 해당 상품의 리뷰 점수의 평균값
    * todo : db와 컬럼명 동일하게 해야함
    * */
    private float averageStars;

    /*
     * 해당 상품에 달린 좋아요 수
     * */
    private int likes;

    /*
    * 해당 상품을 장바구니 담은 수
    * */
    private int baskets;
}
