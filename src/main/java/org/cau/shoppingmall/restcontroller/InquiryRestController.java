package org.cau.shoppingmall.restcontroller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/inquiries")
@Api(tags = {"문의 관련 API"})
@RequiredArgsConstructor
public class InquiryRestController {

    @GetMapping
    @ApiOperation(value = "상품의 문의 목록 확인", notes="query parameter로 넘긴 id를 가진 상품의 문의 정보를 페이징하여 반환합니다. 페이지 정보가 필수로 요구됩니다.")
    public Object getOrderByUser(
            @ApiParam(value="상품 id", example = "1")
            @RequestParam Long itemId,
            @ApiParam(value="페이지", example= "1")
            @RequestParam(required = true) int page) {

        return null;
    }
}
