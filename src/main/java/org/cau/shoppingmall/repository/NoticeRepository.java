package org.cau.shoppingmall.repository;

import org.cau.shoppingmall.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice,Long> {
}
