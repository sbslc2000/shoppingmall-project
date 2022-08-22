package org.cau.shoppingmall.repository;

import org.cau.shoppingmall.entity.user.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
