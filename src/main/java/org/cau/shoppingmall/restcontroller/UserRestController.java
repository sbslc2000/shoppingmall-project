package org.cau.shoppingmall.restcontroller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.cau.shoppingmall.dto.Users.UserDto;
import org.cau.shoppingmall.dto.Users.UserForm;
import org.cau.shoppingmall.dto.Users.UserUpdateForm;
import org.cau.shoppingmall.dto.orders.OrderDto;
import org.cau.shoppingmall.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

// 사용자 url -> controller 찾음 -> 비즈니스로직 -> 페이지(뷰렌더링), 데이터(데이터 던져주는데)

@RestController
@RequestMapping("api/users")
@Api(tags = {"유저 관련 API"})
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    // 유저 정보 추가


    // 유저 수정
    @PutMapping("/user/{id}")
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
    @GetMapping("/user/{id}")
    @ApiOperation(value = "유저정보 반환", notes = "UserId를 전송하면 등록된 User의 정보가 반환됩니다.")
    public UserDto getUser(@PathVariable("userId") Long userId){

        return userService.get(userId);
    }

    // 유저 삭제
    @DeleteMapping("/user/{id}")
    @ApiOperation(value = "유저 회원탈퇴", notes = "UserId를 전송하면 유저 정보가 삭제됩니다.")
    public void deleteUser(@PathVariable("userId") Long userId){
        userService.delete(userId);
    }

    @PostMapping("/isDuplicate")
    @ApiOperation(value = "회원 아이디 중복 검사", notes = "id를 전송하면 중복된 아이디인지에 대한 결과를 반환한다.\n" +
            "true : 중복, false : 중복아님")
    public boolean isDuplicateUsername(
            @ApiParam(value = "유저 아이디",example = "sbslc2000")
            @RequestParam("username") String username
    ) {
        return false;
    }
}
