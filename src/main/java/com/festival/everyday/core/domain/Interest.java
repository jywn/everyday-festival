package com.festival.everyday.core.domain;

import com.festival.everyday.core.domain.user.Company;
import com.festival.everyday.core.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 축제 -> 업체 관심 보내기
 * SIGNAL 은 예약어였습니다.... INTEREST 로 돌아가겠습니다 ...
 */

@Entity
@Getter
@NoArgsConstructor
@Table(name ="interest")
public class Interest {

    @Id @GeneratedValue
    @Column(name = "interest_id")
    private Long id;

    /**
     * 업체가 자신에게 관심을 표한 축제를 조회할 일은 없다.
     * 따라서 반대 방향은 설계하지 않는다.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private User company;

    /**
     * 축제는 자신이 관심을 표한 업체의 지원서를 표시한다.
     * 반대 방향을 설계한다.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "festival_id")
    private Festival festival;

    /**
     * 축제와 업체의 관심 관계를 연결합니다.
     */
    public Interest(Festival festival, Company company) {
        this.festival = new Festival();
        this.company = new Company();
    }

    /**
     * 작성 규칙
     * 1. 이 위의 코드는 가능한 수정하지 않습니다. 필요한 경우 다같이 논의한 후 수정합니다.
     * 2. @Setter 는 절대 선언하지 않습니다. 필요한 경우 메서드 단위로 직접 제작합니다.
     * 3. 그럼에도 불구하고, 세터 메서드도 가능한 제작하지 않습니다. 필요한 경우 사용 의도가 나타나도록 이름을 작성합니다.
     */
}
