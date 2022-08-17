package org.cau.shoppingmall.dto.orders;

import lombok.Data;

@Data
public class OrderedItemForm {

    private Long itemId;
    private Long sizeId;
    private Long colorId;
    private Integer quantity;
}
