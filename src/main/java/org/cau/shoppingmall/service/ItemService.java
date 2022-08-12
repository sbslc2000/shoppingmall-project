package org.cau.shoppingmall.service;

import org.cau.shoppingmall.dto.ItemForm;
import org.cau.shoppingmall.entity.item.Item;

import java.util.List;
public interface ItemService {

    /*
        List<Item> getHotItems : 누적 판매량이 높은 순서대로 8개의 상품을 반환한다.
    */
    List<Item> getHotItems();

    /*
     *  Item get : 아이디에 해당하는 상품을 반환한다.
     * */
    Item get(Long itemId);

    Item update(ItemForm form, Long itemId);




}
