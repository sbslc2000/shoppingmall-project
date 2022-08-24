package org.cau.shoppingmall.dto.orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/*
* OrderForm 에서 사용되는 객체
* */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderedItemForm {

    private Long itemId;
    private Long sizeId;
    private Long colorId;
    private Integer quantity;
}
