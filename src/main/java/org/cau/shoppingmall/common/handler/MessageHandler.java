package org.cau.shoppingmall.common.handler;

import org.cau.shoppingmall.common.dto.MessageDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

public class MessageHandler {
    // 사용자에게 메시지를 전달하고, 페이지를 리다이렉트 한다.
    public static String showMessageAndRedirect(final MessageDto params, Model model) {
        model.addAttribute("params", params);
        return "common/messageRedirect";
    }
}
