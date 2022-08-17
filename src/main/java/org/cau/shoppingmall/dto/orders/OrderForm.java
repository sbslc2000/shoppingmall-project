package org.cau.shoppingmall.dto.orders;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.cau.shoppingmall.entity.order.*;
import springfox.documentation.spring.web.json.Json;

import java.time.LocalDateTime;

@Data
@Builder
@ToString
public class OrderForm {

    private 

    private String ordererName;

    private String ordererContact;

    private String recipientName;

    private String recipientContact;

    private String locationCode;

    private String location;

    private String locationDetail;

    private String shippingRequirement;

    private Boolean termsAndConditionsAccepted;

    private String orderedItems;

    private int pointUsed;

    private Long paymentMethod;

    private boolean cashReceiptFlag;

    private String cashReceiptType;

    private String cashReceiptNumber;

    public Orders toEntity(User user, OrderProcess orderProcess, Payment payment) {
        return new Orders().builder()
                .user(user)
                .orderProcess(orderProcess)
                .registerDate(LocalDateTime.now())
                .ordererName(ordererName)
                .ordererContact(ordererContact)
                .recipientName(recipientName)
                .recipientContact(recipientContact)
                .shippingAddress(locationCode+":"+location+":"+locationDetail)
                .shippingRequirements(shippingRequirement)
                .payment(payment)
                .termsAndConditionsAccepted(termsAndConditionsAccepted)
                .build();
    }

    public Payment createPayment(PaymentMethod paymentMethod, int paymentPrice, CashReceipt cashReceipt) {
        return new Payment().builder()
                .paymentFlag(false)
                .paymentMethod(paymentMethod)
                .paymentPrice(paymentPrice)
                .pointUsed(pointUsed)
                .cashReceiptFlag(cashReceiptFlag)
                .cashReceipt(cashReceipt)
                .build();
    }

    public CashReceipt createCashReceipt() {
        return new CashReceipt().builder()
                .type(cashReceiptType)
                .number(cashReceiptNumber)
                .build();
    }
}
