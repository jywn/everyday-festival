package com.festival.everyday.core.notice.repository;

import com.festival.everyday.core.notice.domain.Notice;
import com.festival.everyday.core.notice.dto.command.NoticeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoticeRepositoryCustom {
    public Page<NoticeDto> findNoticeList(Long receiverId, Pageable pageable);
}
