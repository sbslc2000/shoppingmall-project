package org.cau.shoppingmall.repository.item;

import org.cau.shoppingmall.entity.item.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ColorRepository extends JpaRepository<Color,Long> {

    Optional<Color> findByName(String name);
}
