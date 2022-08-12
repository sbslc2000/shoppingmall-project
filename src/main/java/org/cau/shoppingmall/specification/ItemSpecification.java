package org.cau.shoppingmall.specification;

import org.cau.shoppingmall.entity.item.Item;
import org.springframework.data.jpa.domain.Specification;

public class ItemSpecification {

    public static Specification<Item> nameLike(String searchWord){
        return (root,query,criteriaBuilder) -> criteriaBuilder.like
    }
}
