package org.cau.shoppingmall.repository;

import org.cau.shoppingmall.entity.inquiry.OneToOneInquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface OneToOneInquiryRepository extends JpaRepository<OneToOneInquiry,Long> {


    List<OneToOneInquiry> findByUserId(Long userId);
}
