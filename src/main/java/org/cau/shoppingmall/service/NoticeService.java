package org.cau.shoppingmall.service;

import org.cau.shoppingmall.dto.notice.NoticeDto;
import org.cau.shoppingmall.dto.notice.NoticeForm;

import java.util.List;

public interface NoticeService {
    Long createNotice(NoticeForm form, Long userId);

    List<NoticeDto> get();
}
