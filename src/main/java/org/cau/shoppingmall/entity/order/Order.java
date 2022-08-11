package org.cau.shoppingmall.entity.order;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * TODO: OrderProcess Entity와 연관관계 매핑
     *  db와 컬럼명 통일
     * */
    private Long orderProcessId;

    /*
     * TODO: db와 컬럼명 통일
     * */
    private LocalDateTime registerDate;

    private String ordererName;

    private String ordererContact;

    private String recipientName;

    private String recipientContact;

    private String shippingAddress;

    private String shippingRequirements;

    /*
    * TODO: Payment Entity 와 연관관계 매핑
    * */
    private Long paymentId;

    /*
    * 약관 동의 여부
    * */
    private boolean termsAndConditionsAccepted;



}
