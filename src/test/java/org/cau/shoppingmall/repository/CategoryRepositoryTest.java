package org.cau.shoppingmall.repository;

import org.cau.shoppingmall.entity.item.Category;
import org.cau.shoppingmall.entity.item.Size;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SizeRepository sizeRepository;

    @Test
    void insertSize() {
        Size m = new Size().builder()
                .name("M")
                .build();

        Size l = new Size().builder()
                .name("L")
                .build();

        Size free = new Size().builder()
                .name("Free")
                .build();

        sizeRepository.save(m);
        sizeRepository.save(l);
        sizeRepository.save(free);
    }

    //@Test
    void insert() {
        Category c1 = new Category().builder()
                .name("의류")
                .build();

        Category c2 = new Category().builder()
                .name("뷰티")
                .build();

        Category c3 = new Category().builder()
                .name("가방/지갑")
                .build();

        Category c4 = new Category().builder()
                .name("액세사리")
                .build();

        Category c5 = new Category().builder()
                .name("신발")
                .build();

        Category c6 = new Category().builder()
                .name("레드불")
                .build();

        //categoryRepository.save(c1);
       // categoryRepository.save(c2);
        //categoryRepository.save(c3);
        //categoryRepository.save(c4);
        categoryRepository.save(c6);

    }


}