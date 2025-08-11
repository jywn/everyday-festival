package com.festival.everyday.core.domain.application;

import com.festival.everyday.core.domain.recruit.ExtraQuestion;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 어떠한 의미도 가지지 않습니다.
 * 다만, 추가 질문은 지원서마다 재사용합니다.
 * 따라서 다대다 관계가 발생하였으므로, 별도의 테이블로 분리하였습니다.
 * -> 지원서에 질문을 연결하는 엔티티가 되겠습니다.
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "application_extra_question")
public class ApplicationExtraQuestion {

    @Id @GeneratedValue
    @Column(name = "application_extra_question_id")
    private Long id;

    /**
     * 양방향 연관관계입니다.
     * 지원서를 통해 질문을 조회합니다.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    private Application application;

    /**
     * 단방향 연관관계입니다.
     * 질문을 통해 지원서를 조회하지는 않습니다.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "extraQuestion_id")
    private ExtraQuestion extraQuestion;

    /**
     * 외부 호출 불가능 생성자.
     * 정적 팩토리 메서드에서 사용합니다.
     */
    private ApplicationExtraQuestion(Application application, ExtraQuestion extraQuestion) {
        this.application = application;
        this.extraQuestion = extraQuestion;
    }

    /**
     * 단일 공통 진입점.
     * 외부에서 호출 가능합니다.
     * 지원서와 추가 질문을 연결합니다.
     */
    public static List<ApplicationExtraQuestion> createApplicationExtraQuestions(Application application, ExtraQuestion... extraQuestions) {
        List<ApplicationExtraQuestion> applicationExtraQuestions = new ArrayList<>();

        for (ExtraQuestion extraQuestion : extraQuestions) {
            applicationExtraQuestions.add(new ApplicationExtraQuestion(application, extraQuestion));
        }

        application.addApplicationExtraQuestions(applicationExtraQuestions);
        return applicationExtraQuestions;
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
