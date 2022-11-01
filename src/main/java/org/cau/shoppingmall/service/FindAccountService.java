package org.cau.shoppingmall.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cau.shoppingmall.dto.Users.FindUserIdForm;
import org.cau.shoppingmall.dto.Users.FindUserPasswordForm;
import org.cau.shoppingmall.entity.user.User;
import org.cau.shoppingmall.exception.notfound.UserNotFoundException;
import org.cau.shoppingmall.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindAccountService {

    private final UserRepository userRepository;

    public String validateAndGetFilteredUserId(FindUserIdForm form) throws UserNotFoundException{
        User findUser = userRepository.findByUserNameAndPhoneNumber(form.getUserName(), form.getPhoneNumber()).orElseThrow(
                () -> new UserNotFoundException("해당 정보를 가진 사용자를 찾을 수 없습니다.")
        );

        return filterUserId(findUser.getUserId());
    }

    void validateAndSendEmailContainsUserPassword(FindUserPasswordForm form) {

    }

    private String filterUserId(String userId) {
        return userId.substring(0,userId.length()-4) + "****";
    }
}
