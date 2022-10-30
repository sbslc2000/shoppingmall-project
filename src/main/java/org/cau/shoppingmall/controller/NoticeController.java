package org.cau.shoppingmall.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cau.shoppingmall.common.dto.MessageDto;
import org.cau.shoppingmall.common.handler.MessageHandler;
import org.cau.shoppingmall.dto.notice.NoticeDto;
import org.cau.shoppingmall.dto.notice.NoticeForm;
import org.cau.shoppingmall.exception.notfound.NoAuthInfoFoundException;
import org.cau.shoppingmall.service.LoginService;
import org.cau.shoppingmall.service.NoticeService;
import org.cau.shoppingmall.service.UserService;
import org.cau.shoppingmall.user.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class NoticeController {

    private final NoticeService noticeService;
    private final LoginService loginService;

    private final UserService userService;

    @GetMapping("/notices")
    public String showNoticePage(Model model) {


        List<NoticeDto> noticeList = noticeService.get();
        model.addAttribute("noticeList",noticeList);
        return "notice/notice";
    }

    @GetMapping("/notice-form")
    public String noticeForm(Model model, HttpSession session,RedirectAttributes redirect) {

        try {
            UserDetails userDetails = loginService.getLoginedUserData(session);

            if(userDetails.getAuthority().getId().equals(1L)) {

                MessageDto message = new MessageDto("공지 작성 권한이 없습니다.", "/notices", RequestMethod.GET, null);
                return MessageHandler.showMessageAndRedirect(message,model);
            }

            model.addAttribute("noticeForm",new NoticeForm());
            return "notice/notice_write";
        } catch (NoAuthInfoFoundException e) {
            MessageDto message = new MessageDto("로그인이 필요한 서비스입니다.", "/login", RequestMethod.GET, null);
            return MessageHandler.showMessageAndRedirect(message,model);
        }
    }

    @PostMapping("/notice")
    public String createNotice(@ModelAttribute NoticeForm form,
                               HttpSession session, RedirectAttributes redirect) {

        try {
            UserDetails userDetails = loginService.getLoginedUserData(session);

            if(userDetails.getAuthority().getId().equals(1L)) {
                String message = "공지 작성 권한이 없습니다.";
                List<NoticeDto> noticeList = noticeService.get();
                redirect.addFlashAttribute("noticeList",noticeList);
                try {
                    return "redirect:/notices?error=true&msg="+URLEncoder.encode(message,"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
            }


            //정상적인 권한일시
            Long noticeId = noticeService.createNotice(form, userDetails.getId());

            List<NoticeDto> noticeList = noticeService.get();
            redirect.addFlashAttribute("noticeList",noticeList);
            return "redirect:/notices";
        } catch (NoAuthInfoFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
