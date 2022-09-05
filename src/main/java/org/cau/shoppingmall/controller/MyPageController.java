package org.cau.shoppingmall.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cau.shoppingmall.exception.NoAuthInfoFoundException;
import org.cau.shoppingmall.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MyPageController {

    private final LoginService loginService;


    /*
    * 마이페이지 출력. 내용은 피그마 "마이페이지" 참조
   * */
    @GetMapping
    public String myPage(HttpSession session , Model model) {
        Long userId;
        try {
            userId = loginService.getUserId(session);
        } catch (NoAuthInfoFoundException e) {
            e.printStackTrace();
        }

        //회원 리포지토리에서 회원 정보 DTO 가져오기

        //model에 thymeleaf에서 출력될 정보 추가하기

        return "mypage/mypage";

    }


    /*
     * 마이페이지-장바구니. 내용은 피그마 "장바구니 클릭시" 참조
     * */
    @GetMapping("/baskets")
    public String baskets(HttpSession session, Model model ) {


        return "mypage/basets";
    }

    /*
     * 마이페이지-좋아요. 내용은 피그마 "좋아요 목록" 참조
     * */
    @GetMapping("/likes")
    public String likes(HttpSession session, Model model ) {


        return "mypage/likes";
    }

    /*
     * 마이페이지-내 정보 수정. 내용은 피그마 "내 정보 수정 클릭 시" 참조
     * */
    @GetMapping("/userUpdate")
    public String userUpdateForm(HttpSession session, Model model ) {


        return "mypage/userUpdate";
    }

    /*
    * 마이페이지 - 리뷰 보기. 내용은 피그마 "내가 쓴 리뷰 목록" 참조
    * */
    @GetMapping("/reviews")
    public String myReviews(HttpSession session, Model model) {


        return "mypage/myReview";
    }

    /*
     * 마이페이지 - 주문 내역 보기. 내용은 피그마 "내가 쓴 주문 목록" 참조
     * */
    @GetMapping("/orders")
    public String myOrders(HttpSession session, Model model) {


        return "mypage/myOrder";
    }

    /*
            User Update 부분 post 컨트롫러는 AccountController에 있음
    */





}