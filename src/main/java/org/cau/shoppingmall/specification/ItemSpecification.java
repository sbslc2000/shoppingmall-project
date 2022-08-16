package org.cau.shoppingmall.specification;

import java.util.List;
import org.cau.shoppingmall.entity.item.Category;
import org.cau.shoppingmall.entity.item.Item;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class ItemSpecification {

    public static Specification<Item> nameLike(String searchWord){
        return (root,query,criteriaBuilder) -> criteriaBuilder.like(root.get("name"),"%"+searchWord+"%");
    }

    public static Specification<Item> priceBetween(int min, int max) {
        return (root,query,criteriaBuilder) -> criteriaBuilder.between(root.get("price"),min,max);
    }

    /*

    public static Specification<Item> categoryIs(Long categoryId) {
        return (root,query,criteriaBuilder) ->  {
            criteriaBuilder.equal(root.get("category_id"),categoryId);
        }
    }

    */


    public static Specification<Item> sellerIs(Long sellerId) {
        return (root,query,criteriaBuilder) -> criteriaBuilder.equal(root.get("seller.id"),sellerId);
    }

    public static Specification<Item> nameLikes(String searchWord) {
        return new Specification<Item>() {
            @Override
            public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("name"),searchWord);
            }
        };
    }

    public static Specification<Item> categoryIs(List<Long> categoryList) {
        Specification<Item> result = ((root, query, criteriaBuilder) -> null);
        for (Long categoryId : categoryList) {
            result = result.or(new Specification<Item>() {
                @Override
                public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                    Path<Category> u = root.get("category");
                    return criteriaBuilder.equal(u.get("id"), categoryId);
                }
            });
        }

        return result;
    }

    public static Specification<Item> sellerIs(List<Long> sellerList) {
        Specification<Item> result = ((root, query, criteriaBuilder) -> null);
        for (Long sellerId : sellerList) {
            result = result.or(new Specification<Item>() {
                @Override
                public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                    Path<Category> u = root.get("seller");
                    return criteriaBuilder.equal(u.get("id"), sellerId);
                }
            });
        }

        return result;
    }
}
