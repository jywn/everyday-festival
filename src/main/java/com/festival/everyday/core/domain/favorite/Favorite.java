package com.festival.everyday.core.domain.favorite;

import com.festival.everyday.core.domain.user.User;
import com.festival.everyday.core.domain.user.UserType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 연관 관계가 존재하지 않습니다.
 * festival 의 존재로 인해 다형적 설계가 어려워 연관관계를 제거하였습니다.
 * 연관 관계가 없더라도, JOIN 쿼리를 사용하면 조회 가능합니다.
 * 이는 연관 관계가 존재하더라도 쿼리를 날리는 것은 동일하니
 * 사실상 메서드 추가 작성 이외에 성능 오버헤드는 없다고 봐도 무방합니다.
 */
@Entity
@Getter
@Table(name ="favorite")
public class Favorite {

    @Id @GeneratedValue
    @Column(name = "favorite_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private User sender;

    @Column(name = "receiver_id", nullable = false)
    private Long receiverId;

    @Enumerated(EnumType.STRING)
    @Column(name = "receiver_type", nullable = false)
    private FavoredType receiverType;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    /**
     * 자기가 보낸 찜 목록만 조회 가능하다.
     * 자신이 받은 찜은 조회가 불가능하다.
     */
    public static Favorite create(User user, Long receiverId, FavoredType receiverType) {
        Favorite favorite = new Favorite();
        favorite.sender = user;
        favorite.receiverId = receiverId;
        favorite.receiverType = receiverType;

        return favorite;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setReceiver(Long receiverId, FavoredType receiverType) {
        this.receiverId = receiverId;
        this.receiverType = receiverType;
    }


    /**
     * 작성 규칙
     * 1. 이 위의 코드는 가능한 수정하지 않습니다. 필요한 경우 다같이 논의한 후 수정합니다.
     * 2. @Setter 는 절대 선언하지 않습니다. 필요한 경우 메서드 단위로 직접 제작합니다.
     * 3. 그럼에도 불구하고, 세터 메서드도 가능한 제작하지 않습니다. 필요한 경우 사용 의도가 나타나도록 이름을 작성합니다.
     */
}
