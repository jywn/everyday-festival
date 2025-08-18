package com.festival.everyday.core.recruit.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static com.festival.everyday.core.common.DomainValidator.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name ="extra_question")
public class ExtraQuestion {

    @Id @GeneratedValue
    @Column(name = "extra_question_id")
    private Long id;

    @NotNull
    @Column(name = "content", nullable = false)
    private String content;

    /**
     * ExtraQuestion 에서 Recruit 를 참조할 가능성은 낮다.
     * 따라서, 단방향으로 설계하는 것이 옳다.
     * 하지만, 반대 방향의 참조가 자주 일어나므로 편의를 위해 양방향으로 설계하였다.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "recruit_id", nullable = false)
    private Recruit recruit;

    /**
     * 필드를 설정하는 생성자. 외부에서 호출하지 못합니다.
     * 정적 랙토리 메서드에서 사용합니다.
     */
    private ExtraQuestion(Recruit recruit, String content) {
        this.content = content;
        this.recruit = recruit;
    }

    /**
     * 단일 공통 진입점.
     * 질문을 생성합니다.
     * 모집 공고와 연결합니다.
     */
    public static List<ExtraQuestion> createQuestions(Recruit recruit, List<String> contents) {
        List<ExtraQuestion> extraQuestions = new ArrayList<>();

        notNull("recruit", recruit);
        notNull("contents", contents);
        for (String content : contents) {
            notNull("content", content);
            extraQuestions.add(new ExtraQuestion(recruit, content));
        }
        recruit.addExtraQuestions(extraQuestions);
        return extraQuestions;
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
