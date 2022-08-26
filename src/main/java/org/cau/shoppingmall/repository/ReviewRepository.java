package org.cau.shoppingmall.repository;

import org.cau.shoppingmall.entity.item.Item;
import org.cau.shoppingmall.entity.user.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByItem(Item item);
}
