package org.cau.shoppingmall.dto.item;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Embeddable;

/*
* Review의 아이템 정보를 다룰때 사용
* */
@Embeddable
@Builder
@Data
public class ItemDetails{

    private Long itemId;

    private Long colorId;

    private Long sizeId;

    public ItemDetails() {

    }
}
