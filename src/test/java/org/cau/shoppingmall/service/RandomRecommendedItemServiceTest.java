package org.cau.shoppingmall.service;

import org.assertj.core.api.Assertions;
import org.cau.shoppingmall.dto.item.ItemDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class RandomRecommendedItemServiceTest {

    @Autowired
    RecommendedItemService recommendedItemService;

    @Test
    @DisplayName("전체 아이템 추천 ")
    void randomTest1() {
        List<ItemDto> recommendedItems = recommendedItemService.get4RecommendedItems(0L);

        recommendedItems.stream().forEach(m -> System.out.println(m));
        assertThat(recommendedItems.size()).isEqualTo(4);
    }

}