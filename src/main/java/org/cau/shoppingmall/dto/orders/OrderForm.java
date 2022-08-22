package org.cau.shoppingmall.dto.orders;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.cau.shoppingmall.entity.order.*;
import org.cau.shoppingmall.entity.user.User;
import springfox.documentation.spring.web.json.Json;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@ToString
@ApiModel(value = "주문 요청 정보", description = "여러 데이터를 가진 domain dto class")
public class OrderForm {

    @ApiModelProperty(value = "주문 아이템 정보 : colorId, sizeId, itemId, quantity 를 가지며, 리스트 형식임")
    private List<OrderedItemForm> orderedItemList;

    @ApiModelProperty(value = "주문자 이름")
    private String ordererName;

    @ApiModelProperty(value = "주문자 연락처")
    private String ordererContact;

    @ApiModelProperty(value = "수령인 이름")
    private String recipientName;

    @ApiModelProperty(value = "수령인 연락처")
    private String recipientContact;

    @ApiModelProperty(value = "우편번호")
    private String locationCode;

    @ApiModelProperty(value = "주소")
    private String location;

    @ApiModelProperty(value = "상세주소")
    private String locationDetail;

    @ApiModelProperty(value = "배송시 요구사항")
    private String shippingRequirement;

    @ApiModelProperty(value = "이용 약관 동의 여부")
    private Boolean termsAndConditionsAccepted;

    @ApiModelProperty(value = "주문한 상품의 수")
    private String orderedItems;

    @ApiModelProperty(value = "사용된 포인트")
    private int pointUsed;

    @ApiModelProperty(value = "결제 수단")
    private Long paymentMethod;

    @ApiModelProperty(value = "현금영수증 여부")
    private boolean cashReceiptFlag;

    @ApiModelProperty(value = "현금영수증 타입 : '개인', '사업자'")
    private String cashReceiptType;

    @ApiModelProperty(value = "현금영수증 번호")
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
                .shippingAddress(locationCode+"\\"+location+"\\"+locationDetail)
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
