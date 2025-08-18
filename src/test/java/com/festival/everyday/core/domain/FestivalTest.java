package com.festival.everyday.core.domain;

import com.festival.everyday.core.common.domain.Address;
import com.festival.everyday.core.common.domain.Period;
import com.festival.everyday.core.user.domain.Holder;
import com.festival.everyday.core.festival.domain.Festival;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class FestivalTest {

    Holder holder = Holder.create("H001", "H001", "H001", "1234-5678", "H001@gmail.com",
            Address.create("서울특별시", "마포구", "월드컵로 1길 2"));

    @Test
    @DisplayName("정상 생성")
    void create() throws Exception {
        Festival festival = Festival.create(holder, "F001", Period.create(LocalDateTime.MIN, LocalDateTime.MAX), "성인 15,000원", "주말은 19시까지 운영합니다.",
                "염리동 노래왕을 찾아서!", "official.com", "1234-567",
                Address.create("서울특별시", "마포구", "월드컵로 1길 2"));

        assertNotNull(festival);
        assertNotNull(festival.getHolder());
        assertEquals("F001", festival.getName());
        assertEquals(LocalDateTime.MIN, festival.getPeriod().getBegin());
    }

    @Test
    @DisplayName("단일 파라미터가 null 이면 예외 발생")
    void create_singleParameterNull_throwsException() {
        assertThrows(IllegalArgumentException.class ,() -> {Festival.create(holder, "F001", Period.create(LocalDateTime.MIN, LocalDateTime.MAX), "성인 15,000원", "주말은 19시까지 운영합니다.",
                null, "official.com", "1234-567",
                Address.create("서울특별시", "마포구", "월드컵로 1길 2"));
        });
    }

    @Test
    @DisplayName("VO 가 null 이면 예외 발생")
    void create_VoNull_throwsException() {
        assertThrows(IllegalArgumentException.class ,() -> {Festival.create(holder, "F001", null, "성인 15,000원", "주말은 19시까지 운영합니다.",
                "염리동 노래왕을 찾아서!", "official.com", "1234-567",
                Address.create("서울특별시", "마포구", "월드컵로 1길 2"));
        });
    }

    @Test
    @DisplayName("Holder 가 null 이면 예외 발생")
    void create_HolderNull_throwsException() {
        assertThrows(IllegalArgumentException.class ,() -> {Festival.create(null, "F001", Period.create(LocalDateTime.MIN, LocalDateTime.MAX), "성인 15,000원", "주말은 19시까지 운영합니다.",
                "염리동 노래왕을 찾아서!", "official.com", "1234-567",
                Address.create("서울특별시", "마포구", "월드컵로 1길 2"));
        });
    }
}