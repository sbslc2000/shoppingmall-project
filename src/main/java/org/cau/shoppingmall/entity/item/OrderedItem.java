package org.cau.shoppingmall.entity.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.cau.shoppingmall.entity.order.Orders;
import org.hibernate.criterion.Order;

import javax.persistence.*;

@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderedItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * TODO: Item과 연관관계 매핑
     * */

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;



    /*
     * TODO: Order Entity와 연관관계 매핑
     * */
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders order;

    public void setOrder(Orders order) {
        if( this.order != null) {
            this.order.getOrderedItemList().remove(this);
        }

        this.order = order;
        order.getOrderedItemList().add(this);
    }

    @ManyToOne
    @JoinColumn(name = "size_id")
    private Size size;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;

    private int quantity;

    /*
     * 리뷰 작성 여부
     * */
    private boolean reviewFlag;

    /*
     * 반품 신청 여부
     * */
    private boolean returnFlag;

    /*
     * 교환 신청 여부
     * */
    private boolean exchangeFlag;

    public void setTrueAtReviewFlag() {
        reviewFlag = true;
    }
}
