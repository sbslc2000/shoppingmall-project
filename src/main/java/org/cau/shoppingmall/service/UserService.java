package org.cau.shoppingmall.service;

import org.cau.shoppingmall.dto.Users.UserDto;
import org.cau.shoppingmall.dto.Users.UserForm;
import org.cau.shoppingmall.dto.Users.UserUpdateForm;
import org.cau.shoppingmall.entity.user.User;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {

    /*
    User create : 사용자에게 입력받은 유저 정보를 생성한다
    프로세스 로직:
    -user entity 생성하기

    점검사항:
    -아이디 중복 체크하기
    */

    @Transactional
    User create(UserForm userForm) throws IllegalArgumentException;

    /*
            User get : Id에 해당하는 user 정보를 반환한다
            프로세스 로직:
            -session에서 ID 확인 후 반환

            */
    UserDto get(Long userId);

    /*
    User update : 사용자가 수정한 유저 정보를 반영한다
    프로세스 로직:
    - 인자로 넘어온 내용을 User 정보에 반영한다

    DB 변경사항:
    -User entity에 해당하는 내용 변경
    */
    User update(Long userId, UserUpdateForm form) throws Exception;

    /*
    User delete : 사용자에게 삭제 요청을 받은 유저 정보를 삭제한다
    프로세스 로직:
    - 요청이 들어온 User의 id에 해당하는 DB 삭제

    DB 변경사항:
    -User 삭제
    */
    void delete(Long userId);


}
