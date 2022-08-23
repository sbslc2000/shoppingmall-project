package org.cau.shoppingmall.restcontroller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
@Api(tags = {"유저 관련 API"})
@RequiredArgsConstructor
public class UserRestController {



}
