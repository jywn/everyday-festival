package com.festival.everyday.core.notice.dto.response;

import com.festival.everyday.core.notice.domain.NoticeType;
import com.festival.everyday.core.notice.domain.payload.NoticePayload;
import com.festival.everyday.core.notice.dto.command.NoticeDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NoticeResponse {
    private Long senderId;
    private String senderName;
    private NoticeType noticeType;
    private NoticePayload payload;

    public static NoticeResponse from(NoticeDto dto) {
        return new NoticeResponse(dto.getSenderId(), dto.getSenderName(), dto.getNoticeType(), dto.getPayload());
    }
}
