package com.festival.everyday.core.notice.dto.command;

import com.festival.everyday.core.notice.domain.Notice;
import com.festival.everyday.core.notice.domain.NoticeType;
import com.festival.everyday.core.notice.domain.payload.NoticePayload;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class NoticeDto {

    private Long senderId;
    private String senderName;
    private NoticeType noticeType;
    private NoticePayload payload;
    private LocalDateTime createdAt;

    public static NoticeDto from(Notice notice) {
        return new NoticeDto(notice.getSenderId(), notice.getSenderName(), notice.getType(), notice.getPayload(), notice.getCreatedAt());
    }
}
