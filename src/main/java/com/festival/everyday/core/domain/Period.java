package com.festival.everyday.core.domain;

import com.festival.everyday.core.domain.validate.DomainValidator;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static com.festival.everyday.core.domain.validate.DomainValidator.*;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Period {

    @NotNull
    private LocalDateTime begin;

    @NotNull
    private LocalDateTime end;

    /**
     * 외부에서 호출 불가능한 생성자.
     * 정적 팩토리 메서드에서 사용합니다.
     */
    private Period(LocalDateTime begin, LocalDateTime end) {
        this.begin = begin;
        this.end = end;
    }

    /**
     * 단일 공통 진입점.
     * 기간을 생성합니다.
     */
    public static Period create(LocalDateTime begin, LocalDateTime end) {
        notNull("begin", begin);
        notNull("end", end);
        return new Period(begin, end);
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
