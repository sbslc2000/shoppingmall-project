package org.cau.shoppingmall.dto.Users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cau.shoppingmall.entity.item.Item;
import org.cau.shoppingmall.entity.user.ShoppingBasket;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasketDto {

    private Long id;

    private Item item;

    public static BasketDto of(ShoppingBasket shoppingBasket) {

        return new BasketDto().builder()
                .id(shoppingBasket.getId())
                .item(shoppingBasket.getItem())
                .build();
    }

}
