package com.festival.everyday.core.domain.recruit;

import com.festival.everyday.core.domain.common.value.Period;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExtraQuestionTest {

    Period period = Period.create(LocalDateTime.MIN, LocalDateTime.MAX);
    LaborRecruit laborRecruit = LaborRecruit.create(period, "공지", "빵 반죽", "일급 15,000원", "힘들다.");
    CompanyRecruit companyRecruit = CompanyRecruit.create(period, "공지", "용모단정");
    String[] str = {"A", "B", "C", "D", "E"};

    @Test
    @DisplayName("근로자 공고 추가 질문 정상 생성")
    void labor_create() throws Exception {
        List<ExtraQuestion> extraQuestions = ExtraQuestion.createQuestions(laborRecruit, str);
        assertNotNull(extraQuestions);

        for (ExtraQuestion extraQuestion : extraQuestions) {
            assertNotNull(extraQuestion);
            assertEquals(extraQuestion.getRecruit(), laborRecruit);
        }

        Assertions.assertThat(extraQuestions)
                .extracting(ExtraQuestion::getContent)
                .containsExactly(str);
    }

    @Test
    @DisplayName("업체 공고 추가 질문 정상 생성")
    void company_create() throws Exception {
        List<ExtraQuestion> extraQuestions = ExtraQuestion.createQuestions(companyRecruit, str);
        assertNotNull(extraQuestions);

        for (ExtraQuestion extraQuestion : extraQuestions) {
            assertNotNull(extraQuestion);
            assertEquals(extraQuestion.getRecruit(), companyRecruit);
        }

        Assertions.assertThat(extraQuestions)
                .extracting(ExtraQuestion::getContent)
                .containsExactly(str);
    }

    @Test
    @DisplayName("질문 내용 누락")
    void create_nullContent_throwException() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> {ExtraQuestion.createQuestions(laborRecruit, null);
        });
    }

    @Test
    @DisplayName("공고 누락")
    void create_nullRecruit_throwException() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> {ExtraQuestion.createQuestions(null, str);});
    }



}