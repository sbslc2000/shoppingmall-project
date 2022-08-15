package org.cau.shoppingmall.restcontroller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/items")
@Api(tags = {"상품 관련 API"})
@RequiredArgsConstructor
public class ItemRestController {

    @GetMapping("/{itemId}")
    @ApiOperation(value = "id에 해당하는 상품 정보 가져오기", notes="id에 해당하는 상품 정보를 얻을 수 있습니다.")
    public Object getItem(@PathVariable("itemId") Long itemId) {
        return null;
    }

    @GetMapping
    @ApiOperation(value = "조건에 해당하는 상품 정보 가져오기", notes = "query parameter로 아래와 같은 내용들과 값을 넣어주면 json 형태로 아이템에 대한 정보를 얻을 수 있습니다.")
    public Object getItemBy(
            @ApiParam(value="카테고리 id", example = "1")
            @RequestParam(required = false) Long category,
            @ApiParam(value = "브랜드/회사 id",example = "1")
            @RequestParam(required = false) Long brand
    ){
        return null;
    }

    @GetMapping("/likes")
    @ApiOperation(value="'좋아요'한 상품 목록", notes = "query parameter로 유저에 대한 정보를 전송하면 해당 유저가 좋아요한 상품 목록을 json 형식으로 얻을 수 있습니다. 페이지 정보를 필수로 넘겨줘야 합니다.")
    public Object getItemsLikes(
            @ApiParam(value="사용자 id", example="3")
            @RequestParam Long userId,
            @ApiParam(value = "페이지", example = "0")
            @RequestParam int page
    ) {
        return null;
    }

    @GetMapping("/baskets")
    @ApiOperation(value = "장바구니 목록", notes = "query parameter로 유저에 대한 정보와 함께 전송하면 해당 유저가 장바구니에 담은 상품 목록을 json 형식으로 얻을 수 있습니다\n페이지 정보를 필수로 넘겨줘야 합니다.")
    public Object getItemBaskets(
            @ApiParam(value = "사용자 id", example="2")
            @RequestParam Long userId,
            @ApiParam(value = "페이지", example = "0")
            @RequestParam int page
    ) {
        return null;
    }



}
