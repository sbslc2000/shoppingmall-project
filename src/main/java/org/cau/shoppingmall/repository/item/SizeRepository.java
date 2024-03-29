package org.cau.shoppingmall.repository.item;

import org.cau.shoppingmall.entity.item.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SizeRepository extends JpaRepository<Size,Long> {

    Optional<Size> findByName(String name);
}
