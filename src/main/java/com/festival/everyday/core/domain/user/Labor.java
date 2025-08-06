package com.festival.everyday.core.domain.user;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("Labor")
@Getter
public class Labor extends User {

    @Column(name = "labor_name")
    private String name;

    @Column(name = "labor_tel")
    private String tel;

    @Column(name = "labor_email")
    private String email;

    @Column(name = "labor_age")
    private Integer age;

    /**
     * MALE, FEMALE, ...
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "labor_gender")
    private Gender gender;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    /**
     * 작성 규칙
     * 1. 이 위의 코드는 가능한 수정하지 않습니다. 필요한 경우 다같이 논의한 후 수정합니다.
     * 2. @Setter 는 절대 선언하지 않습니다. 필요한 경우 메서드 단위로 직접 제작합니다.
     * 3. 그럼에도 불구하고, 세터 메서드도 가능한 제작하지 않습니다. 필요한 경우 사용 의도가 나타나도록 이름을 작성합니다.
     */
}
