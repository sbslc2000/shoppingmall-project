package org.cau.shoppingmall.entity.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.cau.shoppingmall.entityinterface.ShoppingDataInterface;


import javax.persistence.*;

@Builder
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingData implements ShoppingDataInterface {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "count")
    private int count;

    @Column(name = "point")
    private int point;

    @Column(name = "baskets")
    private int baskets;

    @Column(name = "likes")
    private int likes;

    @Column(name = "reviews")
    private int reviews;

    @Override
    public void raiseSalesCount() {

    }

    @Override
    public void changePointAmount(int amount) {
        this.point += amount;
    }

    @Override
    public void addReviews() {
        reviews++;
    }
}
