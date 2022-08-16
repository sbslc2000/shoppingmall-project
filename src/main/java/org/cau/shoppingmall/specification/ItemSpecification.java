package org.cau.shoppingmall.specification;

import org.cau.shoppingmall.entity.item.Item;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;

public class ItemSpecification {

    public static Specification<Item> nameLike(String searchWord){
        return (root,query,criteriaBuilder) -> criteriaBuilder.like(root.get("name"),"%"+searchWord+"%");
    }

    public static Specification<Item> priceBetween(int min, int max) {
        return (root,query,criteriaBuilder) -> criteriaBuilder.between(root.get("price"),min,max);
    }

    public static Specification<Item> categoryIs(Long categoryId) {
        return (root,query,criteriaBuilder) -> criteriaBuilder.equal(root.get("category.id"),categoryId);
    }

    public static Specification<Item> sellerIs(Long sellerId) {
        return (root,query,criteriaBuilder) -> criteriaBuilder.equal(root.get("seller.id"),sellerId);
    }




}
