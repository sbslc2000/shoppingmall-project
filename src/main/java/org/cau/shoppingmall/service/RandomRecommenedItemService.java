package org.cau.shoppingmall.service;

import lombok.RequiredArgsConstructor;
import org.cau.shoppingmall.dto.item.ItemDto;
import org.cau.shoppingmall.dto.item.ItemRequest;
import org.cau.shoppingmall.repository.item.CategoryRepository;
import org.cau.shoppingmall.repository.item.ItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RandomRecommenedItemService implements RecommendedItemService {

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final ItemService itemService;

    @Override
    public List<ItemDto> get4RecommendedItems(Long categoryId) {

        if (categoryId.equals(0L)) {

            Long max = itemRepository.count();

            List<Long> randomNum = get4RandomNumberUnder(max);

            List<ItemDto> result = randomNum.stream().map(l -> ItemDto.of(itemRepository.findById(l).orElseThrow(
                    () -> new NoSuchElementException("찾는 아이템이 없습니다.")
            ))).collect(Collectors.toList());

            return result;
        } else {
            ItemRequest req = new ItemRequest();
            List<Long> categoryList = new ArrayList<>();
            categoryList.add(categoryId);
            req.setCategory(categoryList);
            Page<ItemDto> items = itemService.getItemsByConditions(req);
            List<ItemDto> content = items.getContent();
            Integer contentSize = (Integer)(content.size());
            if(content.size() > 4) {
                List<Long> randomNumber = get4RandomNumberUnder(contentSize.longValue());

                List<ItemDto> result = randomNumber.stream().map(m ->
                        content.get(m.intValue())).collect(Collectors.toList());

                return result;
            } else {
                return content;
            }
        }
    }

    List<Long> get4RandomNumberUnder(Long max) {

        List<Long> result = new ArrayList<>();

        int count = 0;
        boolean[] check = new boolean[1000];
        Random random = new Random();

        while(count != 4) {
            random = new Random();
            Integer i = random.nextInt(max.intValue() - 1) + 1;

            if(check[i] == false) {
                result.add(i.longValue());
                check[i] = true;
                count++;
            }
        }
        return result;
    }
}
