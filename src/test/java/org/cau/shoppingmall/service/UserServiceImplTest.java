package org.cau.shoppingmall.service;

import org.assertj.core.api.Assertions;
import org.cau.shoppingmall.dto.Users.FindUserIdForm;
import org.cau.shoppingmall.entity.user.User;
import org.cau.shoppingmall.util.UserUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceImplTest {

    @Autowired
    private UserUtil userUtil;

    @Autowired
    private UserService userService;


    @Test
    @DisplayName("아이디 찾기 테스트")
    void validateAndGetUserId() {

        User user = userUtil.createUser();

        FindUserIdForm findUserIdForm = new FindUserIdForm();
        findUserIdForm.setUserName("서범석");
        findUserIdForm.setPhoneNumber("01042645540");
        String result = userService.validateAndGetUserId(findUserIdForm);


        //then
        assertThat(result).isEqualTo("sbslc****");
    }

    @Test
    @DisplayName("아이디 찾기시 잘못된 정보가 들어왔을때")
    void validateAndGetUserId2() {

        User user = userUtil.createUser();
        FindUserIdForm findUserIdForm = new FindUserIdForm();
        findUserIdForm.setUserName("서범석");
        findUserIdForm.setPhoneNumber("01042645541");

        NoSuchElementException thrown = assertThrows(NoSuchElementException.class,
                () -> userService.validateAndGetUserId(findUserIdForm));


        assertThat(thrown.getMessage()).isEqualTo("해당 정보를 가진 사용자를 찾을 수 없습니다.");


    }

    @Test
    @DisplayName("비밀번호 찾기 테스트")
    void passwordTest() {

    }


    @Test
    @DisplayName("잘못된 정보가 들어왔을때")
    void passwordTest2() {
        
    }
}