package com.festival.everyday.core.domain.recruit;

import com.festival.everyday.core.domain.common.value.Period;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExtraQuestionRelationTest {

    Period period = Period.create(LocalDateTime.MIN, LocalDateTime.MAX);
    CompanyRecruit companyRecruit = CompanyRecruit.create(period, "공지", "용모단정");
    String[] str = {"A", "B", "C", "D", "E"};

    @Test
    @DisplayName("공고에 추가 질문 목록을 등록합니다.")
    void createWithRecruit() {
        List<ExtraQuestion> questions = ExtraQuestion.createQuestions(companyRecruit, str);
        Assertions.assertThat(questions.getFirst().getRecruit()).isEqualTo(companyRecruit);
        Assertions.assertThat(questions.getFirst().getRecruit().getExtraQuestions().containsAll(questions)).isTrue();
    }

}