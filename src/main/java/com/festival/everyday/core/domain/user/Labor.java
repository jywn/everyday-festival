package com.festival.everyday.core.domain.user;

import com.festival.everyday.core.domain.Address;
import com.festival.everyday.core.domain.Festival;
import com.festival.everyday.core.domain.application.Application;
import com.festival.everyday.core.domain.recruit.Recruit;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("Labor")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Labor extends User {

    @NotNull
    @Column(name = "labor_age")
    private Integer age;

    /**
     * MALE, FEMALE, ...
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "labor_gender")
    private Gender gender;

    @Override
    public UserType getUserType() {
        return UserType.LABOR;
    }

    /**
     * 외부에서 사용 불가능한 생성자.
     * 정적 팩토리 메서드에서 사용합니다.
     */
    private Labor(String account, String password, String name, String tel, String email, Address address, Integer age, Gender gender) {
        super(account, password, name, tel, email, address);
        this.age = age;
        this.gender = gender;
    }

    /**
     * 단일 공통 진입점.
     * Labor 를 생성합니다.
     */
    public static Labor create(String account, String password, String name, String tel, String email, Address address, Integer age, Gender gender) {
        return new Labor(account, password, name, tel, email, address, age, gender);
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
