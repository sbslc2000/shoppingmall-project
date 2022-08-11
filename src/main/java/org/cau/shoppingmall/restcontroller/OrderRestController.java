package org.cau.shoppingmall.restcontroller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("api/orders")
@Api(tags = {"주문 관련 API"})
@RequiredArgsConstructor
public class OrderRestController {

    @GetMapping("/{orderId}")
    @ApiOperation(value = "id에 해당하는 주문 정보 가져오기", notes="id에 해당하는 주문 정보를 얻을 수 있습니다.")
    public Object getOrder(
            @ApiParam(value = "주문 id", example="3")
            @PathVariable("orderId") Long orderId) {
        return null;
    }

    @GetMapping
    @ApiOperation(value = "유저의 주문 정보 확인", notes="query parameter로 넘긴 id를 가진 유저가 갖고있는 주문 정보를 반환합니다. 조건을 넘겨주면 조건에 해당하는 정보만 반환합니다.\n" +
            "페이지 정보를 넘겨주면 페이징한 결과로 반환됩니다.")
    public Object getOrderByUser(
            @ApiParam(value="유저 id", example = "1")
            @RequestParam(required = true) Long userId,
            @ApiParam(value="주문 상태", example= "배송중")
            @RequestParam(required = false) String state,
            @ApiParam(value = "시작 기준 일자", example = "20220802")
            @RequestParam(value = "startDate",required = false)Date startDate,
            @ApiParam(value = "끝 기준 일자", example = "20220808")
            @RequestParam(value = "endDate",required = false)Date endDate,
            @ApiParam(value = "페이지", example = "1")
            @RequestParam(required = false) int page) {

        return null;
    }

}
