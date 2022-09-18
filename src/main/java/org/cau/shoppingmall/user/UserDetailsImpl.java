package org.cau.shoppingmall.user;

import org.cau.shoppingmall.dto.Users.UserDto;
import org.cau.shoppingmall.entity.user.Authority;
import org.cau.shoppingmall.entity.user.User;


public class UserDetailsImpl implements UserDetails{
    private User user;

    public UserDetailsImpl(User user){
        this.user = user;
    }

    @Override
    public Long getId() {
        return user.getId();
    }

    @Override
    public Authority getAuthority() {
        return user.getAuthority();
    }

    @Override
    public UserDto getUser() {
        return UserDto.of(user);
    }
}
