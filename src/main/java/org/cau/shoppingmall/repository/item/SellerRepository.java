package org.cau.shoppingmall.repository.item;

import org.cau.shoppingmall.entity.item.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface SellerRepository extends JpaRepository<Seller,Long> {

    Optional<Seller> findByName(String name);
}
