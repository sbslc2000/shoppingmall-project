package org.cau.shoppingmall.restcontroller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.cau.shoppingmall.dto.Users.FindUserIdForm;
import org.cau.shoppingmall.dto.Users.UserDto;
import org.cau.shoppingmall.dto.Users.UserForm;
import org.cau.shoppingmall.dto.Users.UserUpdateForm;
import org.cau.shoppingmall.dto.orders.OrderDto;
import org.cau.shoppingmall.entity.user.User;
import org.cau.shoppingmall.exception.notfound.UserNotFoundException;
import org.cau.shoppingmall.repository.UserRepository;
import org.cau.shoppingmall.service.FindAccountService;
import org.cau.shoppingmall.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

// 사용자 url -> controller 찾음 -> 비즈니스로직 -> 페이지(뷰렌더링), 데이터(데이터 던져주는데)

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/users")
@Api(tags = {"유저 관련 API"})
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final FindAccountService findAccountService;

    // 유저 정보 추가


    // 유저 수정
    @PutMapping("/{id}")
    @ApiOperation(value = "유저 정보 업데이트", notes = "UserId와 UserForm 형태를 전송하면 유저 정보가 변경됩니다..")
    public void updateUser(
            HttpSession session,
            @RequestParam UserUpdateForm userForm){
        Long userId = 1L;
        try{
            userService.update(userId, userForm);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    // 유저 상세
    @GetMapping("/{id}")
    @ApiOperation(value = "유저정보 반환", notes = "UserId를 전송하면 등록된 User의 정보가 반환됩니다.")
    public UserDto getUser(@PathVariable("userId") Long userId) throws UserNotFoundException {

        return userService.get(userId);
    }

    // 유저 삭제
    /*
    * fixme : 굉장히 위험함. session에서 유효성 검사를 한번 해서 진행
    * */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "유저 회원탈퇴", notes = "UserId를 전송하면 유저 정보가 삭제됩니다.")
    public void deleteUser(@PathVariable("userId") Long userId){
        userService.delete(userId);
    }



    @GetMapping("/isDuplicate")
    @ApiOperation(value = "회원 아이디 중복 검사", notes = "id를 전송하면 중복된 아이디인지에 대한 결과를 반환한다.\n" +
            "true : 중복, false : 중복아님")
    public boolean isDuplicateUsername(
            @ApiParam(value = "유저 아이디",example = "sbslc2000")
            @RequestParam("username") String username
    ) {
        Optional<User> findUser = userRepository.findByUserId(username);

        if(findUser.isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    @GetMapping("/findID")
    @ApiOperation(value = "회원 아이디 찾기", notes = "회원 이름과 전화번호를 입력하면 아이디를 반환한다.")
    public String findIdHandler(String userName,
                                String phoneNumber) {

        FindUserIdForm form = new FindUserIdForm(userName, phoneNumber);

        try {
            return findAccountService.validateAndGetFilteredUserId(form);
        } catch (UserNotFoundException e) {
            return e.getMessage();
        }
    }

    @PostMapping("/findPW")
    @ApiOperation(value = "회원 비밀번호 찾기")
    public void findPWHandler() {

    }
}
