package com.festival.everyday.core.domain.user;

import com.festival.everyday.core.domain.common.value.Address;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {

    @Test
    @DisplayName("정상 생성")
    public void create() throws Exception {
        Company company = Company.create("C001", "C001", "C001", "1234-5678", "C001@gmail.com",
                Address.create("서울특별시", "마포구", "월드컵로 1길 2"),
                "맛집입니다.", "ig.com", "원종윤", Category.FOOD, "1234a567");

        assertNotNull(company);
        assertEquals("C001", company.getAccount());
        assertEquals("월드컵로 1길 2", company.getAddress().getDetail());
        assertEquals(Category.FOOD, company.getCategory());
    }

    @Test
    @DisplayName("단일 파라미터가 null 이면 예외 발생")
    public void create_singleParameterNull_throwsException() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> {
            Company.create(null, "C001", "C001", "1234-5678", "C001@gmail.com",
                    Address.create("서울특별시", "마포구", "월드컵로 1길 2"),
                    "맛집입니다.", "ig.com", "원종윤", Category.FOOD, "1234a567");
        });
    }

    @Test
    @DisplayName("VO null 이면 예외 발생")
    public void create_VoNull_throwsException() throws Exception {
        // 값타입 null
        assertThrows(IllegalArgumentException.class, () -> {
            Company.create(null, "C001", "C001", "1234-5678", "C001@gmail.com",
                    null,
                    "맛집입니다.", "ig.com", "원종윤", Category.FOOD, "1234a567");
        });
    }

    @Test
    @DisplayName("Enum null 이면 예외 발생")
    public void create_enumNull_throwsException() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> {
            Company.create(null, "C001", "C001", "1234-5678", "C001@gmail.com",
                    Address.create("서울특별시", "마포구", "월드컵로 1길 2"),
                    "맛집입니다.", "ig.com", "원종윤", null, "1234a567");
        });
    }

}