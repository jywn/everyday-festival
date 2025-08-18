package com.festival.everyday.core.domain.recruit;

import com.festival.everyday.core.common.domain.Period;
import com.festival.everyday.core.recruit.domain.LaborRecruit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class LaborRecruitTest {

    Period period = Period.create(LocalDateTime.MIN, LocalDateTime.MAX);

    @Test
    @DisplayName("정상 생성")
    void create() throws Exception {
        LaborRecruit laborRecruit = LaborRecruit.create(period, "공지", "빵 반죽", "일급 15,000원", "힘들다.");
        assertNotNull(laborRecruit);
        assertEquals(period, laborRecruit.getPeriod());
        assertEquals("공지", laborRecruit.getNotice());
    }

    @Test
    @DisplayName("VO 가 null 이면 예외 발생")
    void create_VoNull_throwsException() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> {
            LaborRecruit.create(null, "공지", "빵 반죽", "일급 15,000원", "힘들다.");
        });
    }

    @Test
    @DisplayName("단일 파라미터가 null 이면 예외 발생")
    void create_singleParameterNull_throwsException() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> {
            LaborRecruit.create(period, null, "빵 반죽", "일급 15,000원", "힘들다.");
        });
    }

}