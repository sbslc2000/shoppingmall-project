package org.cau.shoppingmall.restcontroller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.cau.shoppingmall.dto.Users.UserDto;
import org.cau.shoppingmall.dto.Users.UserForm;
import org.cau.shoppingmall.entity.user.User;
import org.cau.shoppingmall.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/users")
@Api(tags = {"유저 관련 API"})
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

/*
    @PostMapping
    @ApiOperation(value = "유저 회원가입", notes = "UserForm 형태를 전송하면 유저 정보가 등록됩니다.")
    public void insert(@RequestParam UserForm userForm){
        userService.create(userForm);
    }

    //회원가입 페이지
    @GetMapping
    public String joinPage() {
        //view Resolver

        return "user/join";
    }

    @GetMapping("/login")
    public String loginPage() {


        return "user/login";
    }



    //유저 상세보기
    @GetMapping("{/id}")
    public String getUser(@PathVariable Long userId){

        return "user/detail";
    }*/


}
