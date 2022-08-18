package org.cau.shoppingmall.entity.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import springfox.documentation.spring.web.json.Json;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;


@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    /*
     * todo: seller entity와 연관관계 매핑
     * */
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    private int price;

    /*
     * todo: StockDetails entity와 연관관계 매핑
     *  양방향으로 진행
     * */

    @Builder.Default
    @OneToMany(mappedBy = "item",fetch = FetchType.EAGER)
    private List<StockDetails> stockDetailsList = new ArrayList<>();

    /*
     * 해당 상품의 전체 재고량
     * */
    private int quantity;

    /*
     * 상품의 누적 판매량
     * */
    private int sales;

    /*
     * TODO: Category Entity와 연관관계 매핑
     *  단방향으로 진행
     * */

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    /*
     * String 형식으로 이미지의 주소를 담고 있음
     * */
    @Column(length = 3600)
    private String img;


    /*
     * 해당 상품에 달린 리뷰의 개수
     * */
    private int reviews;

    /*
     * 해당 상품의 리뷰 점수의 평균값
     * todo : db와 컬럼명 동일하게 해야함
     * */
    private float averageStars;

    /*
     * 해당 상품에 달린 좋아요 수
     * */
    private int likes;

    /*
     * 해당 상품을 장바구니 담은 수
     * */
    private int baskets;

    @Override
    public String toString() {
        return "Item{" +
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

    /*
    * amount 로 넘긴 숫자 만큼 quantity 를 변경한다.
    * */
    public void changeQuantity(int amount) {
        this.quantity += amount;
    }

    public void raiseSales(int amount) {
        this.sales += amount;
    }
}
