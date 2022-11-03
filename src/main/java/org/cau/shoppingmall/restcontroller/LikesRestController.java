package org.cau.shoppingmall.restcontroller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/likes")
@Api(tags = {"좋아요 관련 API"})
public class LikesRestController {

    @ApiOperation(value="좋아요 생성")
    @PostMapping()
    public void addLikes() {
        return;
    }
}
