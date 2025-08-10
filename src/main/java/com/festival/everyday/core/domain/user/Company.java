package com.festival.everyday.core.domain.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

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

    @NotBlank
    @Column(name = "company_name")
    private String name;

    @NotBlank
    @Column(name = "company_introduction")
    private String introduction;

    //공식 홈페이지가 없는 영세 업체를 고려하여 공백 문자까지 허용합니다.
    @URL
    @NotNull
    @Column(name = "company_link")
    private String link;

    @NotBlank
    @Column(name = "ceo_name")
    private String ceoName;

    @NotBlank
    @Column(name = "company_tel")
    private String tel;

    @Email
    @NotBlank
    @Column(name = "company_email")
    private String email;

    /**
     * FOOD, ART, ...
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "company_category")
    private Category category;


    // 데모 버전에서 임의의 문자열을 삽입하도록 하였습니다.
    @Column(name = "business_registration_number")
    private String business_registration_number = UUID.randomUUID().toString();

    @Override
    public UserType getUserType() {
        return UserType.COMPANY;
    }

    /**
     * 작성 규칙
     * 1. 이 위의 코드는 가능한 수정하지 않습니다. 필요한 경우 다같이 논의한 후 수정합니다.
     * 2. @Setter 는 절대 선언하지 않습니다. 필요한 경우 메서드 단위로 직접 제작합니다.
     * 3. 그럼에도 불구하고, 세터 메서드도 가능한 제작하지 않습니다. 필요한 경우 사용 의도가 나타나도록 이름을 작성합니다.
     */
}
