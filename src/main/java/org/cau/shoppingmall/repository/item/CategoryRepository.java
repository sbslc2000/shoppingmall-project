package org.cau.shoppingmall.repository.item;

import org.cau.shoppingmall.entity.item.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
