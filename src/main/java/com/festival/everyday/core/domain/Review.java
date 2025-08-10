package com.festival.everyday.core.domain;

import com.festival.everyday.core.domain.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private User sender;

    /**
     * festival, company
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")
    private User receiver;

}
