package org.cau.shoppingmall.service;

import org.cau.shoppingmall.dto.item.ItemDto;
import java.util.List;

public interface RecommendedItemService {

    List<ItemDto> get4RecommendedItems(Long categoryId);
}
