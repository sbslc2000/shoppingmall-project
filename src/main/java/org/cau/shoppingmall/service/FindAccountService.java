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

    /**
     * todo : 랜덤비밀번호 생성 및 생성된 비밀번호 user 에 적용 후 이메일 보내기
     * */
    void validateAndSendEmailContainsUserPassword(FindUserPasswordForm form) throws UserNotFoundException {

        User user = userRepository.findByUserId(form.getUserId()).orElseThrow(
                () -> new UserNotFoundException("해당 아이디를 가진 사용자를 찾을 수 없습니다.")
        );

        if(form.validate(user)) {
            //비밀번호 랜덤 값으로 변경
            //변경된 비밀번호를 이메일로 전송
        } else {
            throw new UserNotFoundException("해당 정보를 가진 사용자를 찾을 수 없습니다.");
        };
    }

    private String filterUserId(String userId) {
        return userId.substring(0,userId.length()-4) + "****";
    }
}
