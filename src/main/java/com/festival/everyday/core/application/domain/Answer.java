package com.festival.everyday.core.application.domain;

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
@Table(name ="answer")
public class Answer {

    @Id @GeneratedValue
    @Column(name = "answer_id")
    private Long id;

    @NotNull
    @Column(name = "content", nullable = false)
    private String content;

    /**
     * 양방향 연관관계입니다.
     * 지원서는 답변을 조회할 필요가 있습니다.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;

    /**
     * 외부에서 호출 불가능한 메서드.
     * 정적 팩토리 메서드에서 사용합니다.
     */
    private Answer(Application application, String content) {
        this.application = application;
        this.content = content;
    }

    /**
     * 단일 공통 진입점.
     * 외부에서 호출 가능합니다.
     * 답변을 생성합니다.
     */
    public static List<Answer> createAnswers(Application application, List<String> contents) {
        List<Answer> answers = new ArrayList<>();
        notNull("application", application);
        for (String content : contents) {
            notNull("content", content);
            answers.add(new Answer(application, content));
        }

        application.addAnswers(answers);
        return answers;
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
