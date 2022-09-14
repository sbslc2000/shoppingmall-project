package org.cau.shoppingmall.service;

import lombok.RequiredArgsConstructor;
import org.cau.shoppingmall.dto.notice.NoticeDto;
import org.cau.shoppingmall.dto.notice.NoticeForm;
import org.cau.shoppingmall.entity.Notice;
import org.cau.shoppingmall.entity.user.User;
import org.cau.shoppingmall.repository.NoticeRepository;
import org.cau.shoppingmall.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;
    private final UserRepository userRepository;

    @Override
    public Long createNotice(NoticeForm form, Long userId) {
        User user = userRepository.findById(userId).get();

        Notice notice = form.toEntity(user);

        Notice savedNotice = noticeRepository.save(notice);

        return savedNotice.getId();

    }

    @Override
    public List<NoticeDto> get() {
        List<Notice> findNotices = noticeRepository.findAll();

        List<NoticeDto> noticeDtoList = findNotices.stream()
                .map(m -> NoticeDto.of(m))
                .sorted(Comparator.comparing(NoticeDto::getId).reversed())
                .collect(Collectors.toList());

        return noticeDtoList;
    }
}
