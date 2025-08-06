package com.festival.everyday.core.domain.recruit;
import com.festival.everyday.core.domain.Period;
import com.festival.everyday.core.domain.application.Answer;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter
@Table(name ="recruit")
public abstract class Recruit {

    @Id @GeneratedValue
    @Column(name = "recruit_id")
    private Long id;

    private Period period;

    @Column(name = "notice")
    private String notice;

    /**
     * 값 타입 컬렉션 -> 일대다 관계의 테이블 + 영속성 전이 + 고아 제거 를 통해 컬렉션 처럼 사용한다.
     * 질문의 순서가 중요하므로, 값 타입 컬렉션 대신 별도의 엔티티로 설계하였다.
     */
    @OneToMany(mappedBy = "recruit", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExtraQuestion> extraQuestions = new ArrayList<>();

    /**
     * 작성 규칙
     * 1. 이 위의 코드는 가능한 수정하지 않습니다. 필요한 경우 다같이 논의한 후 수정합니다.
     * 2. @Setter 는 절대 선언하지 않습니다. 필요한 경우 메서드 단위로 직접 제작합니다.
     * 3. 그럼에도 불구하고, 세터 메서드도 가능한 제작하지 않습니다. 필요한 경우 사용 의도가 나타나도록 이름을 작성합니다.
     */
}
