package com.festival.everyday.core.domain;

import com.festival.everyday.core.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import java.time.LocalDateTime;

/**
 * festival -> review
 * review -> festival (o): review 조회 api 를 별도로 설계함
 *
 * company -> review
 * review -> company
 *
 * labor -> review
 *
 */
@Entity
@Getter
@Table(name = "review")
public class Review {

    @Id @GeneratedValue
    @Column(name = "review_id")
    private Long id;

    @Column(name = "review_content")
    private String content;

    /**
     * festival, company, labor
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private User sender;

    /**
     * festival, company
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")
    private User receiver;

    @Column(name = "Created_at")
    private LocalDateTime createdAt;


}
