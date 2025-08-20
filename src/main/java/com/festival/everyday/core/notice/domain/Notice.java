package com.festival.everyday.core.notice.domain;

import com.festival.everyday.core.common.domain.BaseCreatedAtEntity;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "notice")
public class Notice extends BaseCreatedAtEntity {

    @Id
    @GeneratedValue
    @Column(name = "notice_id")
    private Long id;

    @NotNull
    @Column(name = "sender_id")
    private Long senderId;

    @NotBlank
    @Column(name = "sender_name")
    private String senderName;

    @NotNull
    @Column(name = "receiver_id")
    private Long receiverId;

    /**
     * NoticeType 에 맞춰 팩토리 생성자 메서드 설계 ? ... 고려중
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "notice_type")
    private NoticeType type;

    /**
     * 알람 종류에 맞춰 JSON 이 payload 로 변환됩니다.
     */
    @Type(JsonType.class)
    @Column(columnDefinition = "json", name = "data")
    private NoticePayload data;

    public static Notice create(Long senderId, String senderName, Long receiverId, NoticeType type, NoticePayload data) {
        Notice notice = new Notice();
        notice.senderId = senderId;
        notice.senderName = senderName;
        notice.receiverId = receiverId;
        notice.type = type;
        notice.data = data;
        return notice;
    }
}
