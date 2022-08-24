package org.cau.shoppingmall.dto.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

/*
* Review의 아이템 정보를 가져올 때 사용
* */
@Embeddable
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDetails{

    private String colorName;

    private String sizeName;

}
