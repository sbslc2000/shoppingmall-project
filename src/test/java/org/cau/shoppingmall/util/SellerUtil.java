package org.cau.shoppingmall.util;

import org.cau.shoppingmall.entity.item.Seller;
import org.cau.shoppingmall.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerUtil {


    @Autowired
    private SellerRepository repository;


    public void saveSellerEntity(String sellerName) {
        Seller seller = new Seller().builder()
                .name(sellerName)
                .build();

        repository.save(seller);
    }
}
