package org.cau.shoppingmall.dto.orders;
/*

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

@ManyToOne
@JoinColumn(name = "order_process_id")
private OrderProcess orderProcess;

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

@OneToOne
@JoinColumn(name = "payment_id")
private Payment payment;


private boolean termsAndConditionsAccepted;
*/

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cau.shoppingmall.entity.order.Orders;
import org.cau.shoppingmall.entity.order.Payment;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private String orderProcess;
    private LocalDateTime registerDate;
    private String ordererName;
    private String ordererContact;
    private String recipientName;
    private String recipientContact;
    private String shippingAddress;
    private String shippingRequirements;
    private Payment payment;
    private boolean termsAndConditionsAccepted;

    public static OrderDto of(Orders orders) {
        return new OrderDto().builder()
                .id(orders.getId())
                .orderProcess(orders.getOrderProcess().getName())
                .registerDate(orders.getRegisterDate())
                .ordererName(orders.getOrdererName())
                .ordererContact(orders.getOrdererContact())
                .recipientName(orders.getRecipientName())
                .recipientContact(orders.getRecipientContact())
                .shippingAddress(orders.getShippingAddress())
                .shippingRequirements(orders.getShippingRequirements())
                .payment(orders.getPayment())
                .termsAndConditionsAccepted(orders.isTermsAndConditionsAccepted())
                .build();
    }
}
