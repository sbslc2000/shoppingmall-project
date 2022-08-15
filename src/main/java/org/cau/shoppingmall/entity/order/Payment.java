package org.cau.shoppingmall.entity.order;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * 결제 여부
     * */
    @Column(name = "payment_flag")
    private boolean paymentFlag;

    /*
     * 결제 수단
     * TODO: PaymentMethod Entity와 연관관계 매핑
     * */

    @ManyToOne
    @JoinColumn(name = "payment_method")
    private PaymentMethod paymentMethod;

    @Column(name = "payment_price")
    private int paymentPrice;

    @Column(name = "point_used")
    private int pointUsed;

    /*
     * 현금영수증 여부
     * */

    @Column(name = "cash_receipt_flag")
    private boolean cashReceiptFlag;

    /*
    * Todo: CashReceipt Entity와 연관관계 매핑핑
    * */

    @OneToOne
    @JoinColumn(name = "cash_receipt_id")
    private CashReceipt cashReceipt;

}
