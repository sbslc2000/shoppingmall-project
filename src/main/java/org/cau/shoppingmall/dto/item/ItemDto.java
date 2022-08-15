package org.cau.shoppingmall.dto.item;

import org.cau.shoppingmall.entity.item.Seller;

/*

           "id=" + id +
                ", name='" + name + '\'' +
                ", seller=" + seller +
                ", price=" + price +
                ", stockDetailsList=" + stockDetailsList +
                ", quantity=" + quantity +
                ", sales=" + sales +
                ", category=" + category +
                ", img='" + img + '\'' +
                ", reviews=" + reviews +
                ", averageStars=" + averageStars +
                ", likes=" + likes +
                ", baskets=" + baskets +
                '}';
    }
 */
public class ItemDto {

    private Long id;

    private String name;

    private int price;

    private Seller seller;

    private int quantity;


}
