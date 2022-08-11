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
@RequestMapping("api/notifications")
@Api(tags = {"알림 관련 API"})
@RequiredArgsConstructor
public class NotificationRestController {

    @GetMapping("/preview")
    @ApiOperation(value = "최근 알림 5개 확인", notes="해당 user가 갖고 있는 알림 중 최근 5개를 반환합니다.")
    public Object get5Notifications(
            @ApiParam(value="유저 id", example = "1")
            @RequestParam(required = true) Long userId
    ) {

        return null;
    }

    @GetMapping
    @ApiOperation(value = "알림 정보 확인", notes="해당 user가 갖고 있는 알림을 페이징하여 반환합니다." +
            "페이지 정보를 넘겨주면 페이징한 결과로 반환됩니다.")
    public Object getNotifications(
            @ApiParam(value="유저 id", example = "1")
            @RequestParam(required = true) Long userId,
            @ApiParam(value="페이지", example = "1")
            @RequestParam(required = true) int page
    ) {

        return null;
    }
}
