package com.festival.everyday.core.festival.domain;

import com.festival.everyday.core.application.domain.Application;
import com.festival.everyday.core.common.domain.Address;
import com.festival.everyday.core.common.domain.BaseCreatedAtEntity;
import com.festival.everyday.core.common.domain.Period;
import com.festival.everyday.core.interest.domain.Interest;
import com.festival.everyday.core.recruit.domain.CompanyRecruit;
import com.festival.everyday.core.recruit.domain.LaborRecruit;
import com.festival.everyday.core.user.domain.Holder;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static com.festival.everyday.core.common.DomainValidator.*;

@Entity
@Getter
@Table(name ="festival")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Festival extends BaseCreatedAtEntity {

    @Id @GeneratedValue
    @Column(name = "festival_id")
    private Long id;

    /**
     * Holder 가 자신의 축제 목록을 조회해야 합니다.
     * 양방향 연관관계입니다.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "holder_id", nullable = false)
    private Holder holder;

    @NotNull
    @Column(name = "festival_name", nullable = false)
    private String name;

    @NotNull
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "begin", column = @Column(name = "hold_begin")),
            @AttributeOverride(name = "end", column = @Column(name = "hold_end"))
    })
    private Period period;

    @NotNull
    @Column(name = "festival_fee", nullable = false)
    private String fee;

    @NotNull
    @Column(name = "festival_time", nullable = false)
    private String time;

    @NotNull
    @Column(name = "festival_introduction", nullable = false)
    private String introduction;

    //공식 홈페이지가 없는 축제를 고려하여 공백 문자까지 허용합니다.
    @NotNull
    @Column(name = "festival_link", nullable = false)
    private String link;

    @NotNull
    @Column(name = "festival_tel", nullable = false)
    private String tel;

    /**
     * 주소를 저장하는 타입입니다.
     * 한 엔티티에서 두 번 이상 사용되는 경우에만 컬럼 명을 지정합니다.
     */
    @NotNull
    @Embedded
    private Address address;

    /**
     * 모집 공고에서 축제를 조회할 일은 없으므로,
     * 단방향 연관관계입니다.
     *
     * */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_recruit")
    private CompanyRecruit companyRecruit;

    /**
     * 모집 공고에서 축제를 조회할 일은 없으므로,
     * 단방향 연관관계입니다.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "labor_recruit")
    private LaborRecruit laborRecruit;

    @OneToMany(mappedBy = "festival")
    private List<Application> applications = new ArrayList<>();

    /**
     * 축제가 자신이 관심보낸 업체를 조회할 수 있어야 하므로,
     * 양방향 연관관계입니다.
     */
    @OneToMany(mappedBy = "festival")
    private List<Interest> interests = new ArrayList<>();

    /**
     * 외부에서 사용 불가능한 생성자입니다.
     * 정적 팩토리 메서드에서 사용합니다.
     */
    private Festival(Holder holder, String name, Period period, String fee, String time, String introduction, String link, String tel, Address address) {
        this.holder = holder;
        this.name = name;
        this.period = period;
        this.fee = fee;
        this.time = time;
        this.introduction = introduction;
        this.link = link;
        this.tel = tel;
        this.address = address;
    }

    /**
     * 단일 공통 진입점.
     * 작성한 공고를 바탕으로 축제를 생성합니다.
     */
    public static Festival create(Holder holder, String name, Period period, String fee, String time, String introduction, String link, String tel, Address address) {
        notNull("holder", holder);
        notNull("name", name);
        notNull("period", period);
        notNull("fee", fee);
        notNull("time", time);
        notNull("introduction", introduction);
        notNull("link", link);
        notNull("tel", tel);
        notNull("address", address);
        Festival festival = new Festival(holder, name, period, fee, time, fee, link, tel, address);
        holder.addFestival(festival);
        return festival;
    }

    /**
     * 자신의 관심 목록을 추가합니다.
     * Interest 에서만 호출 가능합니다.
     */
    public void addInterest(Interest interest) {
        this.interests.add(interest);
    }

    /**
     * 지원 사실을 저장하는 메서드.
     * Application 클래스에서만 호출합니다
     */
    public void addApplication(Application application) {
        applications.add(application);
    }

    /**
     * 업체 지원 공고를 연결합니다.
     * 단방향 연관관계입니다.
     */
    public void addCompanyRecruit(CompanyRecruit companyRecruit) {
        this.companyRecruit = companyRecruit;
    }

    /**
     * 단기 근로자 지원 공고를 연결합니다.
     * 단방향 연관관계입니다.
     */
    public void addLaborRecruit(LaborRecruit laborRecruit) {
        this.laborRecruit = laborRecruit;
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
