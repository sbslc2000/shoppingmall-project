package org.cau.shoppingmall.entity.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.cau.shoppingmall.entityinterface.UserInterface;

import lombok.NoArgsConstructor;
import org.cau.shoppingmall.entityinterface.UserInterface;


import javax.persistence.*;

@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "password")
    private String password;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "birthday")
    private String birthday;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @ManyToOne
    @JoinColumn(name = "shopping_data")
    private ShoppingData shoppingData;

    @ManyToOne
    @JoinColumn(name = "account_data")
    private AccountData accountData;

    @ManyToOne
    @JoinColumn(name = "authority")
    private Authority authority;

    @Override
    public int getPoint() {
        return 0;
    }

}
