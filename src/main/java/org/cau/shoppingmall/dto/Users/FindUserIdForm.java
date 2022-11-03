package org.cau.shoppingmall.dto.Users;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindUserIdForm {

    private String userName;

    private String phoneNumber;

}
