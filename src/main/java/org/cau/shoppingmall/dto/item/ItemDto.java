package org.cau.shoppingmall.dto.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cau.shoppingmall.entity.item.Category;
import org.cau.shoppingmall.entity.item.Item;
import org.cau.shoppingmall.entity.item.Seller;
import org.cau.shoppingmall.entity.item.StockDetails;
import org.cau.shoppingmall.util.ListUtil;

import java.util.ArrayList;
import java.util.List;

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
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {

    private Long id;

    private String name;

    private int price;

    private float stars;

    private Seller seller;

    private Category category;

    private int quantity;

    private int sales;

    private List<String> img;

    private int likes;

    private int reviews;

    private float averageStars;

    private int baskets;

    private List<StockDetailsDto> stockDetailsList;

    public static ItemDto of(Item item) {

        List<StockDetailsDto> stockDetailsDtoList = new ArrayList<>();
        for(StockDetails stockDetails:item.getStockDetailsList()) {
            stockDetailsDtoList.add(StockDetailsDto.of(stockDetails));
        }

        List<String> imgList = ListUtil.parse(item.getImg());

        return new ItemDto().builder()
                .id(item.getId())
                .name(item.getName())
                .price(item.getPrice())
                .stars(item.getAverageStars())
                .seller(item.getSeller())
                .category(item.getCategory())
                .quantity(item.getQuantity())
                .sales(item.getSales())
                .img(imgList)
                .likes(item.getLikes())
                .reviews(item.getReviews())
                .averageStars(item.getAverageStars())
                .baskets(item.getBaskets())
                .stockDetailsList(stockDetailsDtoList)
                .build();
    }


}
