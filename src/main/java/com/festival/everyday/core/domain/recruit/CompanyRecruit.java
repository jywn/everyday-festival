package com.festival.everyday.core.domain.recruit;

import com.festival.everyday.core.domain.common.value.Period;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.festival.everyday.core.domain.validate.DomainValidator.*;

@Entity
@DiscriminatorValue("CompanyRecruit")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CompanyRecruit extends Recruit {

    @NotNull
    @Column(name = "company_preferred", nullable = false)
    private String preferred;

    /**
     * 외부에서 접근 불가능한 메서드입니다.
     * 정적 팩토리 메서드에서 사용합니다.
     */
    private CompanyRecruit(Period period, String notice, String preferred) {
        super(period, notice);
        this.preferred = preferred;
    }

    /**
     * 단일 공통 진입점입니다.
     * 업체 모집 공고를 생성합니다.
     */
    public static CompanyRecruit create(Period period, String notice, String preferred) {
        notNull(preferred, "preferred");
        return new CompanyRecruit(period, notice, preferred);
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
