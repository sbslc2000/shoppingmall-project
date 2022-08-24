package org.cau.shoppingmall.restcontroller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.cau.shoppingmall.dto.Users.UserDto;
import org.cau.shoppingmall.dto.Users.UserForm;
import org.cau.shoppingmall.dto.orders.OrderDto;
import org.cau.shoppingmall.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
@Api(tags = {"유저 관련 API"})
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    // 유저 정보 추가
    @PostMapping("/user/new")
    @ApiOperation(value = "유저 회원가입", notes = "UserForm 형태를 전송하면 유저 정보가 등록됩니다.")
    public void insert(@RequestParam UserForm userForm){
        userService.create(userForm);
    }

    // 유저 수정
    @PutMapping("/user/{id}")
    @ApiOperation(value = "유저 정보 업데이트", notes = "UserId와 UserForm 형태를 전송하면 유저 정보가 변경됩니다..")
    public void updateUser(
            @PathVariable("userId") Long userId,
            @RequestParam UserForm userForm){

        userService.update(userId, userForm);
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


}
