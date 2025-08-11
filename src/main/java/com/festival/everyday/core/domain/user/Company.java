package com.festival.everyday.core.domain.user;

import com.festival.everyday.core.domain.common.value.Address;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

import static com.festival.everyday.core.domain.validate.DomainValidator.*;

@Entity
@DiscriminatorValue("Company")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Company extends User {

    /**
     * @NotEmpty : 공백 금지
     * @NotNull : null 금지 (최소한 공백 문자를 요구합니다.)
     * @NotBlank : @NotEmpty + @NotNull
     */

    @NotNull
    @Column(name = "company_introduction", nullable = false)
    private String introduction;

    //공식 홈페이지가 없는 영세 업체를 고려하여 공백 문자까지 허용합니다.
    @NotNull
    @Column(name = "company_link", nullable = false)
    private String link;

    @NotNull
    @Column(name = "ceo_name", nullable = false)
    private String ceoName;

    /**
     * FOOD, ART, ...
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "company_category", nullable = false)
    private Category category;


    // 데모 버전에서 임의의 문자열을 삽입하도록 하였습니다.
    @Column(name = "business_registration_number", nullable = false)
    private String business_registration_number = UUID.randomUUID().toString();

    @Override
    public UserType getUserType() {
        return UserType.COMPANY;
    }

    /**
     * 외부 호출 불가능한 생성자.
     * 정적 팩토리 메서드에서 사용한다.
     */
    private Company(String account, String password, String name, String tel, String email, Address address, String introduction, String link, String ceoName, Category category) {
        super(account, password, name, tel, email, address);
        this.introduction = introduction;
        this.link = link;
        this.ceoName = ceoName;
        this.category = category;
    }

    /**
     * 단일 공통 진입점.
     * 업체를 생성합니다.
     */
    public static Company create(String account, String password, String name, String tel, String email, Address address, String introduction, String link, String ceoName, Category category, String business_registration_number) {
        notNull("introduction", introduction);
        notNull("link", link);
        notNull("ceoName", ceoName);
        notNull("category", category);
        notNull("business_registration_number", business_registration_number);
        return new Company(account, password, name, tel, email, address, introduction, link, ceoName, category);
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
