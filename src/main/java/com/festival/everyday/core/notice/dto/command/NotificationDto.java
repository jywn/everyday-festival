package com.festival.everyday.core.notice.dto.command;

import com.festival.everyday.core.notice.domain.Notice;
import com.festival.everyday.core.notice.domain.NoticeType;
import com.festival.everyday.core.notice.domain.payload.NoticePayload;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NotificationDto {

    private NoticeType noticeType;

    // 발신 정보 포함
    private NoticePayload payload;

    // 수신 정보
    private Long receiverId;

    public static NotificationDto from(Notice notice) {
        return new NotificationDto(notice.getType(), notice.getPayload(), notice.getReceiverId());
    }
}
