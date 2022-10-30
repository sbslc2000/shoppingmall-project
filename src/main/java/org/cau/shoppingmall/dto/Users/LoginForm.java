package org.cau.shoppingmall.dto.Users;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginForm {

    private String userId;

    private String password;
}
