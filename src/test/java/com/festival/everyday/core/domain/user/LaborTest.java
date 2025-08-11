package com.festival.everyday.core.domain.user;

import com.festival.everyday.core.domain.common.value.Address;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LaborTest {

    @Test
    @DisplayName("정상 생성")
    public void create() throws Exception {
        Labor labor = Labor.create("L001", "L001", "L001", "1234-5678", "L001@gmail.com",
                Address.create("서울특별시", "마포구", "월드컵로 1길 2"), 20, Gender.MALE);

        assertNotNull(labor);
        assertEquals("L001", labor.getAccount());
        assertEquals("월드컵로 1길 2", labor.getAddress().getDetail());
        assertEquals(20, labor.getAge());
        assertEquals(Gender.MALE, labor.getGender());
    }

    @Test
    @DisplayName("단일 파라미터 null 이면 예외 발생")
    public void create_singleParameterNull_throwsException() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> {
            Labor.create(null, "L001", "L001", "1234-5678", "L001@gmail.com",
                    Address.create("서울특별시", "마포구", "월드컵로 1길 2"), 20, Gender.MALE);
        });
    }

    @Test
    @DisplayName("VO 가 null 이면 예외 발생")
    public void create_VoNull_throwsException() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> {
            Labor.create("L001", "L001", "L001", "1234-5678", "L001@gmail.com",
                    null, 20, Gender.MALE);
        });
    }

    @Test
    @DisplayName("ENUM 이 null 이면 예외 발생")
    public void create_EnumNull_throwsException() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> {
            Labor.create(null, "L001", "L001", "1234-5678", "L001@gmail.com",
                    Address.create("서울특별시", "마포구", "월드컵로 1길 2"), 20, null);
        });
    }
}