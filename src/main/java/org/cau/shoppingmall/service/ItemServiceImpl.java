package org.cau.shoppingmall.service;

import lombok.RequiredArgsConstructor;
import org.cau.shoppingmall.dto.ItemForm;
import org.cau.shoppingmall.entity.item.Item;
import org.cau.shoppingmall.repository.ItemRepository;
import org.cau.shoppingmall.specification.ItemSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    private final int BASIC_ITEM_VIEWS_IN_PAGE = 16;

    @Override
    public List<Item> getHotItems() {
        return null;
    }

    @Override
    public Item get(Long itemId) {
        return null;
    }

    @Override
    public Item update(ItemForm form, Long itemId) {
        return null;
    }

    @Override
    public Page<Item> getItemsByConditions(int page, List<Long> categoryIds, Integer minPrice, Integer maxPrice, List<Long> sellerIds, String sortedBy) {

        Specification<Item> spec = (root, query, criteriaBuilder) -> null;

        if (categoryIds != null) {
            Specification<Item> categorySpec = (root, query, criteriaBuilder) -> null;
            for (Long categoryId : categoryIds) {
                categorySpec.or(ItemSpecification.categoryIs(categoryId));
            }
            spec.and(categorySpec);
        }

        Specification<Item> priceSpec = (root, query, criteriaBuilder) -> null;

        if (minPrice != null && maxPrice != null) {
            priceSpec.and(ItemSpecification.priceBetween(minPrice, maxPrice));
            spec.and(priceSpec);
        }

        if (sellerIds != null) {
            Specification<Item> sellerSpec = (root, query, criteriaBuilder) -> null;
            for (Long sellerId : sellerIds) {
                sellerSpec.or(ItemSpecification.sellerIs(sellerId));
            }
            spec.and(sellerSpec);
        }


        Pageable pageable = PageRequest.of(page,BASIC_ITEM_VIEWS_IN_PAGE);

        Sort sort = null;

        if(sortedBy.equals("인기 순")) {
            sort = Sort.by(Sort.Direction.DESC, "sales");
        } else if(sortedBy.equals("낮은 가격순")) {
            sort = Sort.by(Sort.Direction.ASC,"price");
        } else if (sortedBy.equals("높은 가격순")) {
            sort = Sort.by(Sort.Direction.DESC,"price");
        } else if (sortedBy.equals("리뷰 순")) {
            sort = Sort.by(Sort.Direction.DESC,"reviews");
        }

        Page<Item> result = itemRepository.findAll(spec, pageable, sort);

        return result;
    }
}
