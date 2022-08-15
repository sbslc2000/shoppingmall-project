package org.cau.shoppingmall.repository;

import org.cau.shoppingmall.entity.item.StockDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockDetailsRepository extends JpaRepository<StockDetails,Long> {
}
