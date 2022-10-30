package org.cau.shoppingmall.service;

import org.cau.shoppingmall.dto.Users.AuthInfo;
import org.cau.shoppingmall.dto.Users.LoginForm;
import org.cau.shoppingmall.exception.LoginFailedException;
import org.cau.shoppingmall.exception.notfound.NoAuthInfoFoundException;
import org.cau.shoppingmall.user.UserDetails;

import javax.servlet.http.HttpSession;

public interface LoginService {


    /*
    * form 의 데이터를 db와 비교하여 동일할 경우 AuthInfo 에 정보를 담아서 반환, 다른 경우 Exception을 반환한다.
    * */
    public AuthInfo login(LoginForm form,HttpSession session) throws LoginFailedException;

    /*
    * 세션에 로그인 되어있는 사용자의 정보를 담은 객체를 반환
    * */
    UserDetails getLoginedUserData(HttpSession session) throws NoAuthInfoFoundException;
    /*
    *   session에서 로그인 정보를 삭제한다.
    * */
    public void logout(HttpSession session);
}
