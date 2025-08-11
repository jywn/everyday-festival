package com.festival.everyday.core.domain.validate;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 도메인 생성 시 검증 메서드를 구현한 클래스.
 * null 을 검사해서 도메인 무결성을 유지한다.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DomainValidator {

    public static void notNull(String fieldName, Object value) {
        if (value == null) {
            throw new IllegalArgumentException("Domain validator: " + fieldName + " cannot be null");
        }
    }
}
