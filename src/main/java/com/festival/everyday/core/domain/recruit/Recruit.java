package com.festival.everyday.core.domain.recruit;
import com.festival.everyday.core.domain.Period;
import com.festival.everyday.core.domain.application.Application;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static com.festival.everyday.core.domain.validate.DomainValidator.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter
@Table(name ="recruit")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Recruit {

    @Id @GeneratedValue
    @Column(name = "recruit_id")
    private Long id;

    @NotNull
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "begin", column = @Column(name = "recruit_begin")),
            @AttributeOverride(name = "end", column = @Column(name = "recruit_end"))
    })
    private Period period;

    @NotNull
    @Column(name = "notice", nullable = false)
    private String notice;

    /**
     * 모집 공고에 지원한 지원서를 참조할 수 있습니다.
     * 양방향 연관관계입니다.
     */
    @OneToMany(mappedBy = "recruit")
    private List<Application> applications = new ArrayList<>();

    /**
     * 값 타입 컬렉션 -> 일대다 관계의 테이블 + 영속성 전이 + 고아 제거 를 통해 컬렉션 처럼 사용한다.
     * 질문의 순서가 중요하므로, 값 타입 컬렉션 대신 별도의 엔티티로 설계하였다.
     */
    @OneToMany(mappedBy = "recruit", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExtraQuestion> extraQuestions = new ArrayList<>();

    /**
     * 자식 클래스에서 사용할 생성자입니다.
     * 캡슐화를 유지하기 위해
     * 필드는 private,
     * 생성자는 protected 입니다.
     */
    protected Recruit(Period period, String notice, List<ExtraQuestion> extraQuestions) {
        notNull("period", period);
        notNull("notice", notice);
        notNull("extraQuestions", extraQuestions);
        this.period = period;
        this.notice = notice;
        this.extraQuestions = extraQuestions;
    }


    /**
     * 모집 공고에 추가 질문을 저장합니다.
     * extraQuestion 에서만 호출합니다.
     */
    public void addExtraQuestions(List<ExtraQuestion> extraQuestions) {
        this.extraQuestions.addAll(extraQuestions);
    }

    /**
     * 모집 공고에 지원한 지원서를 추가합니다.
     * Application 에서만 호출합니다.
     */
    public void addApplication(Application application) {
        this.applications.add(application);
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
