package org.cau.shoppingmall.dto.Users;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Builder
@Data
public class UserForm {

    private String userId;

    private String password;

    private String confirmPassword;

    private String userName;

    private String birthday;

    private String email;

    private String address;

}
