package org.cau.shoppingmall.service;

import lombok.RequiredArgsConstructor;
import org.cau.shoppingmall.dto.notice.NoticeDto;
import org.cau.shoppingmall.dto.notice.NoticeForm;
import org.cau.shoppingmall.repository.NoticeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;
    @Override
    public Long createNotice(NoticeForm form, Long userId) {
        return null;
    }

    @Override
    public List<NoticeDto> get() {

    }
}
