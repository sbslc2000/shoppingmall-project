package org.cau.shoppingmall.entity.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.cau.shoppingmall.entity.item.OrderedItem;
import org.cau.shoppingmall.entity.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

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

    @Builder.Default
    @OneToMany(mappedBy = "order")
    private List<OrderedItem> orderedItemList = new ArrayList<>();

    /*
    * 약관 동의 여부
    * */
    private boolean termsAndConditionsAccepted;



}
