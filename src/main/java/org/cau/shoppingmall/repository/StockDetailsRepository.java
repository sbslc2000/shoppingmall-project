package org.cau.shoppingmall.repository;

import org.cau.shoppingmall.entity.item.StockDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockDetailsRepository extends JpaRepository<StockDetails, Long> {

    Optional<StockDetails> findByItem_IdAndSize_IdAndColor_Id(Long itemId, Long sizeId, Long colorId) ;
}
