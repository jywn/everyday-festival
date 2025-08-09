package com.festival.everyday.core.domain.recruit;

import com.festival.everyday.core.domain.application.Application;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name ="extra_question")
public class ExtraQuestion {

    @Id @GeneratedValue
    @Column(name = "extra_question_id")
    private Long id;

    @Column(name = "content")
    private String content;

    /**
     * ExtraQuestion 에서 Recruit 를 참조할 가능성은 낮다.
     * 따라서, 단방향으로 설계하는 것이 옳다.
     * 하지만, 반대 방향의 참조가 자주 일어나므로 편의를 위해 양방향으로 설계하였다.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruit_id")
    private Recruit recruit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    private Application application;

    public void changeApplication(Application application) {
        this.application = application;
    }

    public static ExtraQuestion of(String content, Application application) {
        ExtraQuestion extraQuestion = new ExtraQuestion();
        extraQuestion.content = content;
        application.addExtraQuestion(extraQuestion);
        return extraQuestion;
    }
    /**
     * 작성 규칙
     * 1. 이 위의 코드는 가능한 수정하지 않습니다. 필요한 경우 다같이 논의한 후 수정합니다.
     * 2. @Setter 는 절대 선언하지 않습니다. 필요한 경우 메서드 단위로 직접 제작합니다.
     * 3. 그럼에도 불구하고, 세터 메서드도 가능한 제작하지 않습니다. 필요한 경우 사용 의도가 나타나도록 이름을 작성합니다.
     */
}
