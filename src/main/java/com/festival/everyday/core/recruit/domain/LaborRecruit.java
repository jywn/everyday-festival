package com.festival.everyday.core.recruit.domain;

import com.festival.everyday.core.common.domain.Period;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.festival.everyday.core.common.DomainValidator.*;

@Entity
@DiscriminatorValue("LaborRecruit")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LaborRecruit extends Recruit {

    @NotNull
    @Column(name = "labor_job", nullable = false)
    private String job;

    @NotNull
    @Column(name = "wage", nullable = false)
    private String wage;

    @NotNull
    @Column(name = "remark", nullable = false)
    private String remark;

    /**
     * 외부에서 호출 불가능한 생성자입니다.
     */
    private LaborRecruit(Period period, String notice, String job, String wage, String remark) {
        super(period, notice);
        this.job = job;
        this.wage = wage;
        this.remark = remark;
    }

    /**
     * 단일 공통 진입점입니다.
     * 단기 근로자 모집 공고를 생성합니다.
     */
    public static LaborRecruit create(Period period, String notice, String job, String wage, String remark) {
        notNull("job", job);
        notNull("wage", wage);
        notNull("remark", remark);
        return new LaborRecruit(period, notice, job, wage, remark);
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
