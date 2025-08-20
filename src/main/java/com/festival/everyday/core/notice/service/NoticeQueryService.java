package com.festival.everyday.core.notice.service;

import com.festival.everyday.core.notice.domain.Notice;
import com.festival.everyday.core.notice.dto.command.NoticeDto;
import com.festival.everyday.core.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticeQueryService {

    private final NoticeRepository noticeRepository;

    public List<NoticeDto> getNotices(Long userId) {
        return noticeRepository.findByReceiverIdOrderByCreatedAtDesc(userId).stream()
                .map(NoticeDto::from).toList();
    }
}
