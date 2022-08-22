package org.cau.shoppingmall.dto.orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
