package org.cau.shoppingmall.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import springfox.documentation.spring.web.json.Json;

@Data
@Builder
@ToString
public class OrderForm {

    private String ordererName;

    private String ordererContact;

    private String recipientName;

    private String recipientContact;

    private String code;

    private String location;

    private String locationDetail;

    private String requirement;

    private Boolean termsAndConditionsAccepted;

    private String orderedItems;

    private int pointUsed;

    private Long paymentMethod;

    private boolean cashReceiptFlag;

    private String cashReceiptType;

    private String cashReceiptNumber;
}
