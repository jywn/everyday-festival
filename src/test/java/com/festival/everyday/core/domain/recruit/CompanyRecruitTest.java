package com.festival.everyday.core.domain.recruit;

import com.festival.everyday.core.domain.common.value.Period;
import com.festival.everyday.core.domain.user.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CompanyRecruitTest {

    Period period = Period.create(LocalDateTime.MIN, LocalDateTime.MAX);
    List<Category> categories = List.of(Category.ART);

    @Test
    @DisplayName("정상 생성")
    void create() throws Exception {
        CompanyRecruit companyRecruit = CompanyRecruit.create(period, "공지", "용모단정", categories);
        assertNotNull(companyRecruit);
        assertEquals(period, companyRecruit.getPeriod());
        assertEquals("공지", companyRecruit.getNotice());
    }

    @Test
    @DisplayName("VO 가 null 이면 예외 발생")
    void create_VoNull_throwsException() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> {
            CompanyRecruit companyRecruit = CompanyRecruit.create(null, "공지", "용모단정", categories);
        });
    }

    @Test
    @DisplayName("단일 파라미터가 null 이면 예외 발생")
    void create_singleParameterNull_throwsException() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> {
            CompanyRecruit companyRecruit = CompanyRecruit.create(period, null, "용모단정", categories);
        });
    }

}