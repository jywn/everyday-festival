package com.festival.everyday.core.domain.interaction;

import com.festival.everyday.core.domain.common.value.BaseCreatedAtEntity;
import com.festival.everyday.core.domain.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.festival.everyday.core.domain.validate.DomainValidator.*;

@Entity
@Getter
@Table(name = "review",
uniqueConstraints = @UniqueConstraint(
        columnNames = {"sender_id", "sender_type", "receiver_id", "receiver_type"}
))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseCreatedAtEntity {

    @Id @GeneratedValue
    @Column(name = "review_id")
    private Long id;

    /**
     * 공백 문자를 허용한다.
     */
    @NotNull
    @Column(name = "review_content", nullable = false)
    private String content;

    /**
     * festival, company, labor
     * 사용자는 자신이 작성한 리뷰 목록을 조회할 일이 없다.
     * 연관관계는 없다.
     */
    @NotNull
    @JoinColumn(name = "sender_id", nullable = false)
    private Long senderId;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "sender_type", nullable = false)
    private SenderType senderType;

    /**
     * 좋아요를 받은 사람.
     * Festival, Company
     * 수신자는 자신이 받은 리뷰를 조회해야 하지만, 다형성이 성립하지 않으므로
     * 연관관계는 없다.
     */
    @NotNull
    @Column(name = "receiver_id", nullable = false)
    private Long receiverId;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "receiver_type", nullable = false)
    private ReceiverType receiverType;

    /**
     * 외부에서 호출 불가능한 생성자.
     * 정적 팩토리 메서드에서 사용한다.
     */
    private Review(Long senderId, SenderType senderType, Long receiverId, ReceiverType receiverType, String content) {
        this.senderId = senderId;
        this.senderType = senderType;
        this.receiverId = receiverId;
        this.receiverType = receiverType;
        this.content = content;
    }

    /**
     * 단일 공통 진입점.
     * 외부에서 호출 가능하다.
     * 리뷰를 생성한다.
     */
    public static Review create(Long senderId, SenderType senderType, Long receiverId, ReceiverType receiverType, String content) {
        notNull("senderId", senderId);
        notNull("senderType", senderType);
        notNull("receiver", receiverId);
        notNull("receiverType", receiverType);
        notNull("content", content);
        return new Review(senderId, senderType, receiverId, receiverType, content);
    }


    /**
     * ~ 연관 관계 설정 메서드
     * ======================================
     * 비즈니스 메서드 ~
     *
     * 주의 사항
     * 1. 연관관계 설정 메서드는 가능한 수정하지 않습니다. 필요한 경우 다같이 논의한 후 수정합니다.
     * 2. @Setter 는 가능한 선언하지 않습니다.필요한 경우 메서드 단위로 직접 제작합니다.
     * 3. 그럼에도 불구하고, 세터 메서드도 가능한 제작하지 않습니다. 사용 의도가 나타나도록 이름을 작성합니다.
     */

}
