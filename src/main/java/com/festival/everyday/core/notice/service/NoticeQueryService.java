package com.festival.everyday.core.notice.service;

import com.festival.everyday.core.notice.domain.Notice;
import com.festival.everyday.core.notice.dto.command.NoticeDto;
import com.festival.everyday.core.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticeQueryService {

    private final NoticeRepository noticeRepository;

    public Page<NoticeDto> getNotices(Long userId, Pageable pageable) {
        return noticeRepository.findNoticeList(userId, pageable);
    }
}
