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

    //회원가입 페이지
    @PostMapping
    @ApiOperation(value = "회원 등록",notes= "memberForm 과 함께 넘겨주면 회원 가입을 진행합니다.")
    public UserDto newUser(
        @ApiParam(value = "UserForm")
        @ModelAttribute UserForm userForm
        ) {
             User user = userService.create(userForm);
             UserDto result = UserDto.of(user);

             return result;
        }

    //유저 상세보기
    @GetMapping("{id}")
    public String getUser(@PathVariable Long userId){

        return "user/detail";
    }

    @ResponseBody
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
