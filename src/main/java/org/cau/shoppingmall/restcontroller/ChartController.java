package org.cau.shoppingmall.restcontroller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/chart")
@Api(tags = {"차트 관련 API"})
@RequiredArgsConstructor
public class ChartController {

    @GetMapping
    @ApiOperation(value = "검색량 상위 10개", notes="현재 검색량 상위 10개의 상품 정보를 반환합니다.")
    public Object chartHandler(
    ) {
        return null;
    }
}
