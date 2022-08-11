package org.cau.shoppingmall.entity.order;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * 결제 여부
     * */
    private boolean paymentFlag;

    /*
     * 결제 수단
     * TODO: PaymentMethod Entity와 연관관계 매핑
     * */
    private Long paymentMethodId;

    private int paymentPrice;

    private int pointUsed;

    /*
     * 현금영수증 여부
     * */
    private boolean cashReceiptFlag;

    /*
    * Todo: CashReceipt Entity와 연관관계 매핑핑
    * */
    private Long cashReceiptId;

}
