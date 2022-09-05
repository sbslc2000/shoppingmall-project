package org.cau.shoppingmall.repository;

import org.cau.shoppingmall.entity.inquiry.ItemInquiry;
import org.cau.shoppingmall.entity.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ItemInquiryRepository extends JpaRepository<ItemInquiry, Long> {

    List<ItemInquiry> findByItem(Item item);
}
