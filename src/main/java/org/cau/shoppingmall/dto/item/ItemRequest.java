package org.cau.shoppingmall.dto.item;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class ItemRequest {

    private String name;

    private Integer minPrice;

    private Integer maxPrice;

    private List<Long> category;

    private List<Long> seller;

    private String sortBy;

    private int page;
}
