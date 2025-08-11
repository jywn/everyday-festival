package com.festival.everyday.core.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.festival.everyday.core.domain.validate.DomainValidator.*;

/**
 * 프록시 생성을 위한 기본 생성자를 배치하였으나, 외부에서 사용하지 못하도록 PROTECTED 로 설정하였습니다.
 * 별도의 테이블로 등록되지 않는, 값 타입입니다.
 */
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Address {

    @NotNull
    @Column(name = "city", nullable = false)
    private String city;

    @NotNull
    @Column(name = "district", nullable = false)
    private String district;

    @NotNull
    @Column(name = "detail", nullable = false)
    private String detail;

    /**
     * 외부 호출 불가능한 생성자.
     * 정적 팩토리 메서드에서 사용한다.
     */
    private Address(String city, String district, String detail) {
        this.city = city;
        this.district = district;
        this.detail = detail;
    }

    /**
     * 단일 공통 진입점.
     * 주소를 생성한다.
     */
    public static Address create(String city, String district, String detail) {
        notNull(city, "city");
        notNull(district, "district");
        notNull(detail, "detail");
        return new Address(city, district, detail);
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
