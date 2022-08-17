package org.cau.shoppingmall.entity.returnItem;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class ReturnPickup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

}
