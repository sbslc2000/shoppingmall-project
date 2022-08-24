package org.cau.shoppingmall.dto.Users;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserUpdateForm {


    private String password;

    private String confirmPassword;

    private String userName;

    private String birthday;

    private String email;

    private String address;
}
