package org.cau.shoppingmall.dto.orders;

import lombok.Builder;
import lombok.Data;

/*
 * Item 창/ 장바구니 창에서 주문 단으로 갈때 사용하는 dto
 * */
@Builder
@Data
public class OrderItem {
    //입력받을것
    private Long itemId;
    private Long colorId;
    private Long sizeId;
    private Integer quantity;


    //추후에 채워질것
    private String itemName;
    private String colorName;
    private String sizeName;
    private Integer price;
}
