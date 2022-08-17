package org.cau.shoppingmall.service;

import lombok.RequiredArgsConstructor;
import org.cau.shoppingmall.dto.item.ItemForm;
import org.cau.shoppingmall.dto.item.ItemDto;
import org.cau.shoppingmall.dto.item.ItemRequest;
import org.cau.shoppingmall.entity.item.Item;
import org.cau.shoppingmall.repository.ItemRepository;
import org.cau.shoppingmall.specification.ItemSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final ItemRepository itemRepository;

    private final int BASIC_ITEM_VIEWS_IN_PAGE = 16;

    @Override
    public List<ItemDto> getHot8Items() {
        List<Item> items = itemRepository.findTop8ByOrderBySalesDesc();
        List<ItemDto> result = new ArrayList<>();
        for(Item i : items) {
            result.add(ItemDto.of(i));
        }

        return result;
    }

    @Override
    public List<ItemDto> getHot4Items() {
        List<Item> items = itemRepository.findTop4ByOrderBySalesDesc();
        List<ItemDto> result = new ArrayList<>();
        for(Item i : items) {
            result.add(ItemDto.of(i));
        }

        return result;
    }

    @Override
    public ItemDto get(Long itemId){
        Optional<Item> findItem = itemRepository.findById(itemId);

        if(findItem.isPresent()) {
            ItemDto itemDto = ItemDto.of(findItem.get());
            return itemDto;
        } else {
            throw new NoSuchElementException("해당하는 상품이 없습니다.");
        }

    }

    @Override
    public Item update(ItemForm form, Long itemId) {
        return null;
    }

    @Override
    public Page<ItemDto> getItemsByConditions(ItemRequest request) {

        Specification<Item> spec = (root, query, criteriaBuilder) -> null;

        if(request.getName() != null) {
            log.info("name filtering name:"+request.getName());
            spec = spec.and(ItemSpecification.nameLike(request.getName()));
        }


        if (request.getMinPrice() != null && request.getMaxPrice() != null) {
            spec = spec.and(ItemSpecification.priceBetween(request.getMinPrice(), request.getMaxPrice()));
            log.info("min and max");
        } else if (request.getMinPrice() != null) {
            spec = spec.and(ItemSpecification.priceBetween(request.getMinPrice(),987654321));
            log.info("min");
        } else if (request.getMaxPrice() != null) {
            spec = spec.and(ItemSpecification.priceBetween(0, request.getMaxPrice()));
            log.info("max");
        }

        if(request.getCategory() != null) {
            spec = spec.and(ItemSpecification.categoryIs(request.getCategory()));
        }

        if(request.getSeller() != null) {
            spec = spec.and(ItemSpecification.sellerIs(request.getSeller()));
        }


        Sort sort = Sort.by(Sort.Direction.ASC,"id");

        String sortBy = request.getSortBy();


        if(sortBy != null) {

            if(sortBy.equals("인기 순")) {
                sort = Sort.by(Sort.Direction.DESC, "sales");
            } else if(sortBy.equals("낮은 가격순")) {
                sort = Sort.by(Sort.Direction.ASC,"price");
                log.info("낮은 가격순으로 정렬");
            } else if (sortBy.equals("높은 가격순")) {
                sort = Sort.by(Sort.Direction.DESC,"price");
                log.info("높은 가격순으로 정렬");
            } else if (sortBy.equals("리뷰 순")) {
                sort = Sort.by(Sort.Direction.DESC,"reviews");
            }
        }



        Pageable pageable = PageRequest.of(request.getPage(),BASIC_ITEM_VIEWS_IN_PAGE,sort);

        Page<Item> result = itemRepository.findAll(spec, pageable);


        Page<ItemDto> itemDtoList = result.map(m ->
                ItemDto.of(m));


        return itemDtoList;
    }
}
