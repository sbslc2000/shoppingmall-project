package org.cau.shoppingmall.dto.orders;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/*
 * Item 창/ 장바구니 창에서 주문 단으로 갈때 사용하는 dto
 * */
@Data
public class OrderItem {

    public OrderItem() {
        orderItemList = new ArrayList<>();
    }

    //입력받을것
    private Long itemId;
    private Long colorId;
    private Long sizeId;
    private Integer quantity;

    private List<OrderItem> orderItemList;

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    //추후에 채워질것
    private String itemName;
    private String colorName;
    private String sizeName;
    private Integer price;


}
