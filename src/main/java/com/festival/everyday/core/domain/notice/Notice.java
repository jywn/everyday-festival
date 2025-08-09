package com.festival.everyday.core.domain.notice;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "notice")
public class Notice {

    @Id
    @GeneratedValue
    @Column(name = "notice_id")
    private Long id;

    @Column(name = "sender_id")
    private Long senderId;

    @Column(name = "sender_name")
    private String senderName;

    @Column(name = "receiver_id")
    private Long receiverId;

    @Enumerated(EnumType.STRING)
    @Column(name = "notice_type")
    private NoticeType type;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    /**
     * 알람 종류에 맞춰 JSON 이 payload 로 변환됩니다.
     */
    @Type(JsonType.class)
    @Column(columnDefinition = "json", name = "data")
    private NoticePayload data;
}
