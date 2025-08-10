package com.festival.everyday.core.domain;

import com.festival.everyday.core.domain.application.Application;
import com.festival.everyday.core.domain.image.Image;
import com.festival.everyday.core.domain.user.Company;
import com.festival.everyday.core.domain.user.Holder;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name ="festival")
public class Festival {

    @Id @GeneratedValue
    @Column(name = "festival_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "holder_id")
    private Holder holder;

    @Column(name = "festival_name")
    private String name;

    @AttributeOverrides({
            @AttributeOverride(name = "begin", column = @Column(name = "hold_begin")),
            @AttributeOverride(name = "end", column = @Column(name = "hold_end"))
    })
    private Period period;

    @Column(name = "festival_fee")
    private String fee;

    @Column(name = "festival_time")
    private String time;

    @Column(name = "festival_introduction")
    private String introduction;

    @Column(name = "festival_link")
    private String link;

    @Column(name = "festival_tel")
    private String tel;

    /**
     * 주소를 저장하는 타입입니다.
     * 한 엔티티에서 두 번 이상 사용되는 경우에만 컬럼 명을 지정합니다.
     */
    @Embedded
    private Address address;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "company_recruit")
    private RecruitingStatus companyRecruit;

    @Enumerated(EnumType.STRING)
    @Column(name = "labor_recruit")
    private RecruitingStatus laborRecruit;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "image_id")
    private Image image;

    @OneToMany(mappedBy = "festival")
    private List<Application> applications = new ArrayList<>();

    @OneToMany(mappedBy = "festival")
    private List<Interest> interests = new ArrayList<>();

    public void uploadFestival(Holder holder) {
        this.holder = holder;
    }

    public void uploadImage(Image image) {
        this.image = image;
    }

    public void sendInterest(Company company) {
        Interest interest = new Interest(this, company);
        interests.add(interest);
    }

    /**
     * 작성 규칙
     * 1. 이 위의 코드는 가능한 수정하지 않습니다. 필요한 경우 다같이 논의한 후 수정합니다.
     * 2. @Setter 는 절대 선언하지 않습니다. 필요한 경우 메서드 단위로 직접 제작합니다.
     * 3. 그럼에도 불구하고, 세터 메서드도 가능한 제작하지 않습니다. 필요한 경우 사용 의도가 나타나도록 이름을 작성합니다.
     */
}
