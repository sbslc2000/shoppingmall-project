package org.cau.shoppingmall.entity.item;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
public class StockDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
    * TODO: Size Entity와 연관관계 매핑
    * */
    private Long size_id;

    /*
     * TODO: Color Entity와 연관관계 매핑
     * */
    private Long color_id;

    private int quantity;
}
