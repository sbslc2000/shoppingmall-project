package org.cau.shoppingmall.restcontroller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
@Api(tags = {"유저 관련 API"})
@RequiredArgsConstructor
public class UserController {

    @PostMapping
    @ApiOperation(value = "회원 등록",notes= "memberForm 과 함께 넘겨주면 회원 가입을 진행합니다.")
    public String createUser(
    ) {
        return null;
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
