package org.cau.shoppingmall.entity.exchange;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class ExchangePickup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;
}
