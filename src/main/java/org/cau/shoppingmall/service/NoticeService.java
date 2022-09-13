package org.cau.shoppingmall.service;

public interface NoticeService {
    Long createNotice(NoticeForm form,Long userId);

    List<NoticeDto> get();
}
