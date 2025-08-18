package com.festival.everyday.core.interest.domain;

import com.festival.everyday.core.festival.domain.Festival;
import com.festival.everyday.core.common.domain.BaseCreatedAtEntity;
import com.festival.everyday.core.company.domain.Company;
import com.festival.everyday.core.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

import static com.festival.everyday.core.common.DomainValidator.*;

/**
 * 축제 -> 업체 관심 보내기
 * SIGNAL 은 예약어였습니다.... INTEREST 로 돌아가겠습니다 ...
 */

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name ="interest")
public class Interest extends BaseCreatedAtEntity {

    @Id @GeneratedValue
    @Column(name = "interest_id")
    private Long id;

    /**
     * 업체가 자신에게 관심을 표한 축제를 조회할 일은 없다.
     * 단방향 연관관계다.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_id", nullable = false)
    private User company;

    /**
     * 축제는 자신이 관심을 표한 업체의 지원서를 표시한다.
     * 양방향 연관관계다.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "festival_id", nullable = false)
    private Festival festival;

    /**
     * 외부에서 호출 불가능한 생성자입니다.
     * 정적 팩토리 메서드에서 사용합니다.
     */
    private Interest(Company company, Festival festival) {
        this.company = company;
        this.festival = festival;
    }

    /**
     * 단일 공통 진입점입니다.
     * 외부에서 호출 가능합니다.
     * 관심 관계를 생성합니다.
     */
    public static Interest create(Company company, Festival festival) {
        notNull("company", company);
        notNull("festival", festival);
        Interest interest = new Interest(company, festival);
        festival.addInterest(interest);
        return interest;
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