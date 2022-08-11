package org.cau.shoppingmall.entity.item;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
public class OrderedItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
    * TODO: Item과 연관관계 매핑
    * */
    private Long itemId;

    /*
    * TODO: Order Entity와 연관관계 매핑
    * */
    private Long orderId;

    private Long sizeId;

    private Long colorId;

    private int quantity;

    /*
    * 리뷰 작성 여부
    * */
    private boolean reviewFlag;

    /*
     * 반품 신청 여부
     * */
    private boolean returnFlag;

    /*
    * 교환 신청 여부
    * */
    private boolean exchangeFlag;
}
