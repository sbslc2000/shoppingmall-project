package org.cau.shoppingmall.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cau.shoppingmall.dto.notice.NoticeForm;
import org.cau.shoppingmall.exception.NoAuthInfoFoundException;
import org.cau.shoppingmall.service.LoginService;
import org.cau.shoppingmall.service.NoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@Slf4j
public class NoticeController {

    private final NoticeService noticeService;
    private final LoginService loginService;

    @GetMapping("/notice")
    public String showNoticePage() {


        return "notice/notice";
    }

    @PostMapping("/notices")
    public String createNotice(@ModelAttribute NoticeForm form,
                               HttpSession session, RedirectAttributes redirect) {

        try {
            Long userId = loginService.getUserId(session);

            Long noticeId = noticeService.createNotice(form, userId);

            return "redirect:/notice";
        } catch (NoAuthInfoFoundException e) {
            throw new RuntimeException(e);
        }

        return "redirect:/notice";
    }
}
