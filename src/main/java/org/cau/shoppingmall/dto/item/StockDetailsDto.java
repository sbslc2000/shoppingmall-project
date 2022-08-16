package org.cau.shoppingmall.dto.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cau.shoppingmall.entity.item.StockDetails;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockDetailsDto {
    private String size;

    private int quantity;

    private String color;

    public static StockDetailsDto of(StockDetails stockDetails) {

        return new StockDetailsDto().builder()
                .color(stockDetails.getColor().getName())
                .size(stockDetails.getSize().getName())
                .quantity(stockDetails.getQuantity())
                .build();

    }
}
