package org.cau.shoppingmall.entity.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Entity
@Getter
@AllArgsConstructor
public class StockDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="item_id")
    private Item item;

    public void setItem(Item item) {
        if(this.item != null) {
            this.item.getStockDetailsList().remove(this);
        }
        this.item = item;
        item.getStockDetailsList().add(this);
    }

    /*
    * TODO: Size Entity와 연관관계 매핑
    * */

    @ManyToOne
    @JoinColumn(name = "size_id")
    private Size size;

    /*
     * TODO: Color Entity와 연관관계 매핑
     * */

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;

    private int quantity;

    public StockDetails() {

    }

    @Override
    public String toString() {
        return "StockDetails{" +
                "id=" + id +
                ", size=" + size +
                ", color=" + color +
                ", quantity=" + quantity +
                '}';
    }
}
