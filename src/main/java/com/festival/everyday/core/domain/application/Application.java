package com.festival.everyday.core.domain.application;

import com.festival.everyday.core.domain.BaseCreatedAtEntity;
import com.festival.everyday.core.domain.Festival;
import com.festival.everyday.core.domain.recruit.ExtraQuestion;
import com.festival.everyday.core.domain.recruit.Recruit;
import com.festival.everyday.core.domain.user.Company;
import com.festival.everyday.core.domain.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.festival.everyday.core.domain.application.SELECTED.*;

/**
 * 질문, 답변 테이블과
 * 참조 필요에 의해 일대다, 다대일 양방향 연관관계이다.
 * 연관 관계의 주인은 외래키 "application_id" 를 갖는 질문/답변 쪽임을 명심할 것.
 */
@Entity
@Getter
@Table(name ="application")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Application extends BaseCreatedAtEntity {

    @Id @GeneratedValue
    @Column(name = "application_id")
    private Long id;

    /**
     * COMPANY, LABOR
     * 내가 작성한 지원서를 조회해야 한다.
     * 양방향으로 설계한다.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * 축제에 작성된 지원서를 조회해야 합니다.
     * 양방향 연관관계입니다.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "festival_id")
    private Festival festival;

    /**
     * 지원서가 대상으로 삼는 공고.
     * 양방향 연관관계입니다.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruit_id")
    private Recruit recruit;

    /**
     * 추가 질문을 지원서마다 재사용하므로, 다대다 관계가 발생합니다.
     * 양방향 관계로 설정하였습니다.
     */
    @OneToMany(mappedBy = "application", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderColumn(name = "application_extra_question_order")
    private List<ApplicationExtraQuestion> applicationExtraQuestions = new ArrayList<>();

    @OneToMany(mappedBy = "application", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderColumn(name = "answer_order")
    private List<Answer> answers = new ArrayList<>();

    @OneToMany(mappedBy = "application", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderColumn(name = "extra_answer_order")
    private List<ExtraAnswer> extraAnswers = new ArrayList<>();

    /**
     * 최초에는 심사중 상태를 저장합니다.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "application_selected")
    private SELECTED selected = NEUTRAL;

    /**
     * 파라미터를 둘 받는 생성자. 외부에 노출되지 않도록 합니다.
     * 필드 값을 채웁니다.
     * 정적 팩토리 메서드에서 사용합니다.
     */
    private Application(Recruit recruit, User user, Festival festival) {
        this.recruit = recruit;
        this.festival = festival;
        this.user = user;
    }

    /**
     * 외부 클래스(festival, user 등) 에서 사용 가능한 공개 진입점.
     * 양방향 연관관계를 설정합니다.
     */
    public static Application create(Recruit recruit, User user, Festival festival) {
        Application application = new Application(recruit, user, festival);
        user.addApplication(application);
        festival.addApplication(application);
        recruit.addApplication(application);
        return application;
    }

    /**
     * Answer 에서만 호출합니다.
     */
    public void addAnswers(List<Answer> answers) {
        this.answers.addAll(answers);
    }

    /**
     * ExtraAnswer 에서만 호출합니다.
     */
    public void addExtraAnswers(List<ExtraAnswer> extraAnswers) {
        this.extraAnswers.addAll(extraAnswers);
    }

    /**
     * ApplicationExtraQuestion 에서만 호출합니다.
     */
    public void addApplicationExtraQuestions(List<ApplicationExtraQuestion> applicationExtraQuestions) {
        this.applicationExtraQuestions.addAll(applicationExtraQuestions);
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

    /**
     * 지원을 수락합니다.
     */
    public void accept() {
        this.selected = ACCEPTED;
    }

    /**
     * 지원을 거절합니다.
     */
    public void deny() {
        this.selected = DENIED;
    }

}
