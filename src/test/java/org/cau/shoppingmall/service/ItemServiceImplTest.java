package org.cau.shoppingmall.service;

import org.cau.shoppingmall.entity.item.Item;
import org.cau.shoppingmall.entity.item.StockDetails;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemServiceImplTest {

    @Autowired
    private ItemService itemService;


    @Test
    @DisplayName("인기상품 8개 가져오기")
    void get8HotItems() {
        List<Item> hotItems = itemService.getHotItems();

        System.out.println(hotItems);
        for(Item item: hotItems) {
            System.out.println("item.getname = "+item.getName());
            System.out.println("item.category = "+item.getCategory().getName());
            for(StockDetails sd : item.getStockDetailsList())  {
                System.out.println("stockdetails: "+sd.getItem().getName()+" "+sd.getColor().getName()+" "+sd.getSize().getName()+" "+sd.getQuantity());
            }

            System.out.println("item.iamge"+item.getImg());
        }
    }

    @Test
    @DisplayName("조건에 따른 상품 출력")
    void byCondition() {
        List<Long> categoryIds = new ArrayList<>();
        categoryIds.add(1L);
        List<Long> sellerIds = new ArrayList<>();
        sellerIds.add(1L);

        Page<Item> result = itemService.getItemsByConditions(0, categoryIds, 0, 900000, sellerIds, "낮은 가격순");

        List<Item> content = result.getContent();

        for(Item item: content) {
            System.out.println("item.getname = "+item.getName());
            System.out.println("item.category = "+item.getCategory().getName());
            System.out.println("item.price = " + item.getPrice());
            for(StockDetails sd : item.getStockDetailsList())  {
                System.out.println("stockdetails: "+sd.getItem().getName()+" "+sd.getColor().getName()+" "+sd.getSize().getName()+" "+sd.getQuantity());
            }

            System.out.println("item.iamge"+item.getImg());
        }
    }
}