package org.cau.shoppingmall.entity.user;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class AccountData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "late_date")
    private int lateDate;

    @Column(name = "register_date")
    private int registerDate;

}
