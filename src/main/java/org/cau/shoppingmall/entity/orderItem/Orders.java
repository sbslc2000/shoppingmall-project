package org.cau.shoppingmall.entity.orderItem;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * TODO: OrderProcess Entity와 연관관계 매핑
     *  db와 컬럼명 통일
     * */

    @ManyToOne
    @JoinColumn(name = "order_process_id")
    private OrderProcess orderProcess;

    /*
     * TODO: db와 컬럼명 통일
     * */
    @Column(name = "register_date")
    private LocalDateTime registerDate;

    @Column(name = "orderer_name")
    private String ordererName;

    @Column(name = "orderer_contact")
    private String ordererContact;

    @Column(name = "recipient_name")
    private String recipientName;

    @Column(name = "recipient_contact")
    private String recipientContact;

    @Column(name = "shipping_address")
    private String shippingAddress;

    @Column(name = "shipping_requirements")
    private String shippingRequirements;

    /*
    * TODO: Payment Entity 와 연관관계 매핑
    * */

    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    /*
    * 약관 동의 여부
    * */
    private boolean termsAndConditionsAccepted;



}
