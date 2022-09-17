package org.cau.shoppingmall.user;

import org.cau.shoppingmall.dto.Users.UserDto;
import org.cau.shoppingmall.entity.user.Authority;

/*
 * 로그인 서비스에서 getUserData 를 통해 받을 수 있는 객체
 * 세션에 로그인 되어있는 사용자의 정보를 담고 있다.
 * 기능
 * getId : user 의 Long id 반환
 * getAuthority : user의 권한정보 반환
 * getUser : userDto 반환
 * */
public interface UserDetails {

    Long getId();

    Authority getAuthority();

    UserDto getUser();
}
