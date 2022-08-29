package org.cau.shoppingmall.entity.user;

import org.cau.shoppingmall.entity.item.Item;

import javax.persistence.*;

public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
}
