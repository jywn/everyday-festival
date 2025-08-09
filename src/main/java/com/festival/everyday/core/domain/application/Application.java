package com.festival.everyday.core.domain.application;

import com.festival.everyday.core.domain.Festival;
import com.festival.everyday.core.domain.recruit.ExtraQuestion;
import com.festival.everyday.core.domain.recruit.Recruit;
import com.festival.everyday.core.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;

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
public class Application {

    @Id @GeneratedValue
    @Column(name = "application_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "festival_id")
    private Festival festival;

    @OneToMany(mappedBy = "application", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExtraQuestion> extraQuestions = new ArrayList<>();

    @OneToMany(mappedBy = "application", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answers = new ArrayList<>();

    @OneToMany(mappedBy = "application", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExtraAnswer> extraAnswers = new ArrayList<>();

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "application_selected")
    private SELECTED selected = NEUTRAL;

    /**
     * 질문/답변을 등록하고 제거하는 메서드입니다.
     * 해당 메서드들은 주인 엔티티 (답변, 추가질문 등) 에서만 호출 가능하빈다.
     */
    public void addExtraQuestion(ExtraQuestion extraQuestion) {
        extraQuestions.add(extraQuestion);
        extraQuestion.changeApplication(this);
    }

    public void removeExtraQuestion(ExtraQuestion extraQuestion) {
        extraQuestions.remove(extraQuestion);
        extraQuestion.changeApplication(null);
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
        answer.changeApplication(this);
    }

    public void removeAnswer(Answer answer) {
        answers.remove(answer);
        answer.changeApplication(null);
    }

    public void addExtraAnswer(ExtraAnswer extraAnswer) {
        extraAnswers.add(extraAnswer);
        extraAnswer.changeApplication(this);
    }

    public void removeExtraAnswer(ExtraAnswer extraAnswer) {
        extraAnswers.remove(extraAnswer);
        extraAnswer.changeApplication(null);
    }

    /**
     * 수락/거절을 설정하는 메서드입니다.
     */
    public void acceptApplication() {
        this.selected = ACCEPTED;
    }

    public void denyApplication() {
        this.selected = DENIED;
    }

    /**
     * 지원 사실의 조작 (등록, ...)은 Application 엔티티를 통해서만 수행합니다.
     * 연관 관계의 주인 이외의 곳에서 조작은 DB에 반영되지 않습니다.
     * 본 연관관계에서, festival 과 user 는 조회용 엔티티로 작용합니다.
     */
    public void userApplyFestival(User user, Festival festival) {
        this.user = user;
        user.getApplications().add(this);

        this.festival = festival;
        festival.getApplications().add(this);
    }
    /**
     * 작성 규칙
     * 1. 이 위의 코드는 가능한 수정하지 않습니다. 필요한 경우 다같이 논의한 후 수정합니다.
     * 2. @Setter 는 절대 선언하지 않습니다. 필요한 경우 메서드 단위로 직접 제작합니다.
     * 3. 그럼에도 불구하고, 세터 메서드도 가능한 제작하지 않습니다. 필요한 경우 사용 의도가 나타나도록 이름을 작성합니다.
     */

}
