package org.cau.shoppingmall.entity.user;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class ShoppingData {

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

}
