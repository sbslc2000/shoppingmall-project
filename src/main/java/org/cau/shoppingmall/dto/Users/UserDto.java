package org.cau.shoppingmall.dto.Users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cau.shoppingmall.dto.orders.OrderDto;
import org.cau.shoppingmall.entity.order.Orders;
import org.cau.shoppingmall.entity.user.AccountData;
import org.cau.shoppingmall.entity.user.Authority;
import org.cau.shoppingmall.entity.user.ShoppingData;
import org.cau.shoppingmall.entity.user.User;

import javax.persistence.*;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String userId;
    private String userName;
    private String birthday;
    private String email;
    private String address;
    private ShoppingData shoppingData;
    private AccountData accountData;
    private Authority authority;

    public static UserDto of(User user) {
        return new UserDto().builder()
                .id(user.getId())
                .userId(user.getUserId())
                .userName(user.getUserName())
                .birthday(user.getBirthday())
                .email(user.getEmail())
                .address(user.getAddress())
                .shoppingData(user.getShoppingData())
                .accountData(user.getAccountData())
                .authority(user.getAuthority())
                .build();
    }
}
