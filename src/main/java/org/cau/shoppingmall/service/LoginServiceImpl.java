package org.cau.shoppingmall.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cau.shoppingmall.dto.Users.AuthInfo;
import org.cau.shoppingmall.dto.Users.LoginForm;
import org.cau.shoppingmall.entity.user.User;
import org.cau.shoppingmall.exception.LoginFailedException;
import org.cau.shoppingmall.exception.notfound.NoAuthInfoFoundException;
import org.cau.shoppingmall.repository.UserRepository;
import org.cau.shoppingmall.user.UserDetails;
import org.cau.shoppingmall.user.UserDetailsImpl;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;

    /**
     *
     * @param form 로그인 요청 정보 : userId, password
     * @param session
     * @return AuthInfo : 세션에 저장될 로그인 정보
     * @throws LoginFailedException : 로그인 실패시 exception 반환
     */
    @Override
    public AuthInfo login(LoginForm form,HttpSession session) throws LoginFailedException {

        log.info("form id = {}",form.getUserId());

        // 아이디로 회원 찾기
        User user = userRepository.findByUserId(form.getUserId()).orElseThrow(
                () -> new LoginFailedException("해당 아이디가 존재하지 않습니다.")
        );

        // 비밀번호가 맞는지 db와 확인
        if(BCrypt.checkpw(form.getPassword(),user.getPassword())) {

            //세션로그인 객체 생성
            AuthInfo authInfo = new AuthInfo().builder()
                    .id(user.getId())
                    .userId(user.getUserId())
                    .build();

            //최근 접속 일자를 최신화
            user.getAccountData().setLateDateToNow();

            //세션에 로그인정보 저장
            session.setAttribute("authInfo",authInfo);

            return authInfo;
        } else {
            throw new LoginFailedException("아이디나 비밀번호가 맞지 않습니다.");
        }

    }

    @Override
    public UserDetails getLoginedUserData(HttpSession session) throws NoAuthInfoFoundException {
        AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
        if(authInfo == null) {
            throw new NoAuthInfoFoundException("로그인 정보가 없습니다.");
        }

        User user = userRepository.findById(authInfo.getId()).get();

        UserDetailsImpl userDetails = new UserDetailsImpl(user);
        return userDetails;
    }

    @Override
    public void logout(HttpSession session) {
        session.removeAttribute("authInfo");
    }
}
