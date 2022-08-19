package org.cau.shoppingmall.repository;

import org.cau.shoppingmall.entity.item.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long>, JpaSpecificationExecutor<Item> {

    Page<Item> findAll(Specification<Item> specification, Pageable pageable);


    List<Item> findTop8ByCategory_IdOrderBySalesDesc(Long categoryId);

    List<Item> findTop4ByOrderBySalesDesc();
}
