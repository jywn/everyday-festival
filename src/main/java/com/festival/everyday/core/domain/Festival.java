package com.festival.everyday.core.domain;

import com.festival.everyday.core.domain.application.Application;
import com.festival.everyday.core.domain.image.Image;
import com.festival.everyday.core.domain.recruit.CompanyRecruit;
import com.festival.everyday.core.domain.recruit.LaborRecruit;
import com.festival.everyday.core.domain.user.Company;
import com.festival.everyday.core.domain.user.Holder;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "holder_id")
    private Holder holder;

    @NotBlank
    @Column(name = "festival_name")
    private String name;

    @Valid
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "begin", column = @Column(name = "hold_begin")),
            @AttributeOverride(name = "end", column = @Column(name = "hold_end"))
    })
    private Period period;

    @NotBlank
    @Column(name = "festival_fee")
    private String fee;

    @NotBlank
    @Column(name = "festival_time")
    private String time;

    @NotBlank
    @Column(name = "festival_introduction")
    private String introduction;

    //공식 홈페이지가 없는 축제를 고려하여 공백 문자까지 허용합니다.
    @URL
    @NotNull
    @Column(name = "festival_link")
    private String link;

    @NotBlank
    @Column(name = "festival_tel")
    private String tel;

    /**
     * 주소를 저장하는 타입입니다.
     * 한 엔티티에서 두 번 이상 사용되는 경우에만 컬럼 명을 지정합니다.
     */
    @Valid
    @Embedded
    private Address address;

    /**
     * 모집 공고에서 축제를 조회할 일은 없으므로,
     * 단방향 연관관계입니다.
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "company_recruit")
    private CompanyRecruit companyRecruit;

    /**
     * 모집 공고에서 축제를 조회할 일은 없으므로,
     * 단방향 연관관계입니다.
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "labor_recruit")
    private LaborRecruit laborRecruit;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "image_id")
    private Image image;

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
    private Festival(String name, Period period, String fee, String time, String introduction, String link, String tel, Address address) {
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
    public static Festival create(String name, Period period, String fee, String time, String introduction, String link, String tel, Address address) {
        return new Festival(name, period, fee, time, fee, link, tel, address);
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
    public void connectRecruit(CompanyRecruit companyRecruit) {
        this.companyRecruit = companyRecruit;
    }

    /**
     * 단기 근로자 지원 공고를 연결합니다.
     * 단방향 연관관계입니다.
     */
    public void connectLaborRecruit(LaborRecruit laborRecruit) {
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
