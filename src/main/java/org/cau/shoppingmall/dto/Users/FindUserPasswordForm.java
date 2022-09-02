package org.cau.shoppingmall.dto.Users;

import lombok.Data;
import org.cau.shoppingmall.entity.user.User;

@Data
public class FindUserPasswordForm {

    private String userId;

    private String userName;

    private String phoneNumber;

    private String email;

    private String emailAfterAt;


    public boolean validate(User user) {

        if(!userName.equals(user.getUserName()))
            return false;

        if(!phoneNumber.equals(user.getPhoneNumber()))
            return false;

        String fullEmail = email + emailAfterAt;
        if(!fullEmail.equals(user.getEmail()))
            return false;

        return true;

    }
}
