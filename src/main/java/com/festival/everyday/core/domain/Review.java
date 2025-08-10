package com.festival.everyday.core.domain;

import com.festival.everyday.core.domain.favorite.ReceiverType;
import com.festival.everyday.core.domain.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Entity
@Getter
@Table(name = "review",
uniqueConstraints = @UniqueConstraint(
        columnNames = {"sender_id", "receiver_id", "receiver_type"}
))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseCreatedAtEntity {

    @Id @GeneratedValue
    @Column(name = "review_id")
    private Long id;

    @NotBlank
    @Column(name = "review_content")
    private String content;

    /**
     * festival, company, labor
     * 사용자는 자신이 작성한 리뷰 목록을 조회할 일이 없으므로,
     * 단방향 연관관계다.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private User sender;

    /**
     * 좋아요를 받은 사람.
     * Festival, Company
     * 수신자는 자신이 받은 리뷰를 조회해야 하지만, 다형성이 성립하지 않으므로
     * 연관관계는 없다.
     */
    @NotNull
    @Column(name = "receiver_id", nullable = false)
    private Long receiverId;

    @Enumerated(EnumType.STRING)
    @Column(name = "receiver_type", nullable = false)
    private ReceiverType receiverType;

    /**
     * 외부에서 호출 불가능한 생성자.
     * 정적 팩토리 메서드에서 사용한다.
     */
    private Review(User sender, Long receiverId, ReceiverType receiverType, String content) {
        this.sender = sender;
        this.receiverId = receiverId;
        this.receiverType = receiverType;
        this.content = content;
    }

    /**
     * 단일 공통 진입점.
     * 외부에서 호출 가능하다.
     * 리뷰를 생성한다.
     */
    public static Review create(User sender, Long receiverId, ReceiverType receiverType, String content) {
        return new Review(sender, receiverId, receiverType, content);
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
