package org.cau.shoppingmall.entity.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;
@Entity
@Getter
@AllArgsConstructor
public class ShoppingBasket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /*@ManyToOne 해당 부분 오류 핸들링 필요
    @JoinColumn(name = "item_id")
    private Item item;*/

}
