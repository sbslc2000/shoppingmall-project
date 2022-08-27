package org.cau.shoppingmall.dto.Users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserForm {

    private String userId;

    private String password;

    private String confirmPassword;

    private String userName;

    private String birthday;

    private String email;

    private String addressCode;

    private String address;

    private String addressDetails;

}
