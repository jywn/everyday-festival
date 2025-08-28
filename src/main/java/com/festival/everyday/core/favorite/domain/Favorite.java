package com.festival.everyday.core.favorite.domain;

import com.festival.everyday.core.common.domain.BaseCreatedAtEntity;
import com.festival.everyday.core.common.domain.ReceiverType;
import com.festival.everyday.core.user.domain.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.festival.everyday.core.common.DomainValidator.*;

/**
 * 연관 관계가 존재하지 않습니다.
 * festival 의 존재로 인해 다형적 설계가 어려워 연관관계를 제거하였습니다.
 * 연관 관계가 없더라도, JOIN 쿼리를 사용하면 조회 가능합니다.
 * 이는 연관 관계가 존재하더라도 쿼리를 날리는 것은 동일하니
 * 사실상 메서드 추가 작성 이외에 성능 오버헤드는 없다고 봐도 무방합니다.
 */
@Entity
@Getter
@Table(name ="favorite",
uniqueConstraints = @UniqueConstraint(
        columnNames = {"sender_id", "receiver_id", "receiver_type"}
))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Favorite extends BaseCreatedAtEntity {

    @Id @GeneratedValue
    @Column(name = "favorite_id")
    private Long id;

    /**
     * 좋아요를 누른 사람.
     * Holder, Labor, Company
     * 내가 찜을 누른 목록을 조회해야 하므로, 양방향으로 설계한다.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    /**
     * 좋아요를 받은 사람.
     * Festival, Company
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
    private Favorite(User sender, ReceiverType receiverType, Long receiverId) {
        this.sender = sender;
        this.receiverType = receiverType;
        this.receiverId = receiverId;
    }

    /**
     * 외부에서 호출 가능한 단일 공통 진입점.
     * 자신에게 좋아요를 누른 목록을 조회할 필요가 없습니다.
     * 좋아요 관계를 생성합니다.
     */
    public static Favorite create(User sender, ReceiverType receiverType, Long receiverId) {
        notNull("sender", sender);
        notNull("receiver", receiverType);
        notNull("receiverId", receiverId);

        Favorite favorite = new Favorite(sender, receiverType, receiverId);
        sender.addFavorite(favorite);
        return favorite;
    }

    /**
     * 외부에서 호출 가능한 단일 공통 진입점.
     * 좋아요 연관 관계를 제거합니다.
     */
    public static void cancel(User sender, Favorite favorite) {
        sender.removeFavorite(favorite);
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
