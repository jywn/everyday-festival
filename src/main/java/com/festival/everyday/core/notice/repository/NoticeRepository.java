package com.festival.everyday.core.notice.repository;

import com.festival.everyday.core.notice.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long>, NoticeRepositoryCustom {
    List<Notice> findByReceiverIdOrderByCreatedAtDesc(Long receiverId);
}