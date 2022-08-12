package org.cau.shoppingmall.service;

import org.cau.shoppingmall.dto.ItemForm;
import org.cau.shoppingmall.entity.item.Item;
import org.springframework.data.domain.Page;

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

    /*
    * getItemsByConditions : 인자로 넘어온 조건에 따라 상품 리스트를 반환한다.
    * page = 페이지
    * List<Long> categoryIds : 카테고리가 여러개 선택된 경우 전처리를 통하여 리스트 형식으로 카테고리 아이디를 받는다.
    * int minPrice, maxPrice : 가격의 상하한선을 설정한다. maxPrice가 없는 경우 40000원 이상의 모든 상품을 반환한다.
    * List<Long> sellerIds   : 브랜드가 여러개 선택된 경우 전처리를 통하여 리스트 형식으로 seller 아이디를 받는다.
    * String sortedBy        : String 형식으로 정렬 기준을 받는다 . "인기순" "낮은 가격순" "높은 가격순" "리뷰순"
    *
    * 모든 인자는 null이 들어올 수 있으며, null 인 경우 해당 조건을 제외 한 나머지로 필터링한 결과를 반환한다.
    * */
    Page<Item> getItemsByConditions(int page, List<Long> categoryIds, Integer minPrice, Integer maxPrice, List<Long> sellerIds, String sortedBy);
}
