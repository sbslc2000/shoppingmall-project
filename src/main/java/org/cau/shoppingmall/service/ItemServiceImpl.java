package org.cau.shoppingmall.service;

import lombok.RequiredArgsConstructor;
import org.cau.shoppingmall.dto.ItemForm;
import org.cau.shoppingmall.dto.item.ItemDto;
import org.cau.shoppingmall.dto.item.ItemRequest;
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

        return itemRepository.findTop8ByOrderBySalesDesc();
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
    public Page<ItemDto> getItemsByConditions(ItemRequest request) {

        Specification<Item> spec = (root, query, criteriaBuilder) -> null;

        if (request.getCategory() != null) {
            Specification<Item> categorySpec = (root, query, criteriaBuilder) -> null;
            for (Long categoryId : request.getCategory()) {
                categorySpec.or(ItemSpecification.categoryIs(categoryId));
            }

            spec.and(categorySpec);
            System.out.println(spec);
        }

        Specification<Item> priceSpec = (root, query, criteriaBuilder) -> null;

        if (request.getMinPrice() != null && request.getMaxPrice() != null) {
            priceSpec.and(ItemSpecification.priceBetween(request.getMinPrice(), request.getMaxPrice()));
            spec.and(priceSpec);
        }

        if (request.getSeller() != null) {
            Specification<Item> sellerSpec = (root, query, criteriaBuilder) -> null;
            for (Long sellerId : request.getSeller()) {
                sellerSpec.or(ItemSpecification.sellerIs(sellerId));
            }
            spec.and(sellerSpec);
        }




        Sort sort = Sort.by(Sort.Direction.ASC,"id");

        String sortBy = request.getSortBy();

        if(sortBy.equals("인기 순")) {
            sort = Sort.by(Sort.Direction.DESC, "sales");
        } else if(sortBy.equals("낮은 가격순")) {
            sort = Sort.by(Sort.Direction.ASC,"price");
        } else if (sortBy.equals("높은 가격순")) {
            sort = Sort.by(Sort.Direction.DESC,"price");
        } else if (sortBy.equals("리뷰 순")) {
            sort = Sort.by(Sort.Direction.DESC,"reviews");
        }

        Pageable pageable = PageRequest.of(request.getPage(),BASIC_ITEM_VIEWS_IN_PAGE,sort);

        Page<Item> result = itemRepository.findAll(spec, pageable);


        Page<ItemDto> itemDtoList = result.map(m ->
                ItemDto.of(m));


        return itemDtoList;
    }
}
