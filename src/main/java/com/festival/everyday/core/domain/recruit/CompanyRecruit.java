package com.festival.everyday.core.domain.recruit;

import com.festival.everyday.core.domain.common.value.Period;
import com.festival.everyday.core.domain.user.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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
     * 모집하는 업체는 여러 종류를 선택할 수 있습니다.
     * 엔티티로 설계하기에는, 단순 ENUM 만 저장하고
     * 둘 사이의 어떠한 부가 정보도 발생하지 않으므로
     * 컬렉션 테이블로 충분합니다.
     */
    @ElementCollection(fetch = FetchType.LAZY)
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "company_categoreis",
            joinColumns = @JoinColumn(name = "recruit_id")
    )
    @Column(name = "company_category")
    private List<Category> categories = new ArrayList<>();

    /**
     * 외부에서 접근 불가능한 메서드입니다.
     * 정적 팩토리 메서드에서 사용합니다.
     */
    private CompanyRecruit(Period period, String notice, String preferred, List<Category> categories) {
        super(period, notice);
        this.preferred = preferred;
        this.categories = categories;
    }

    /**
     * 단일 공통 진입점입니다.
     * 업체 모집 공고를 생성합니다.
     */
    public static CompanyRecruit create(Period period, String notice, String preferred, List<Category> categories) {
        notNull(preferred, "preferred");
//        notNull(categories, "categories"); validator 수정 필요
        return new CompanyRecruit(period, notice, preferred, categories);
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
