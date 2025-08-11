package com.festival.everyday.core.domain.user;

import com.festival.everyday.core.domain.Address;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 도메인의 메서드를 테스트합니다.
 * */
class HolderTest {

    @DisplayName("NotEmpty")
    @Test
    public void notEmptyTest() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> {
            Holder.create("", "H001", "H001", "1234-5678", "H001@gmail.com", Address.create("서울특별시", "마포구", "월드컵로 1길 2"));
        });
    }

    @DisplayName("NotNull")
    @Test
    public void notNullTest() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> {
            Holder.create(null, "H001", "H001", "1234-5678", "H001@gmail.com", Address.create("서울특별시", "마포구", "월드컵로 1길 2"));
        });
    }

}