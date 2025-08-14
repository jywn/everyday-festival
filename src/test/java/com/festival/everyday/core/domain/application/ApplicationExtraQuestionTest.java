package com.festival.everyday.core.domain.application;

import com.festival.everyday.core.domain.Festival;
import com.festival.everyday.core.domain.common.value.Address;
import com.festival.everyday.core.domain.common.value.Period;
import com.festival.everyday.core.domain.recruit.CompanyRecruit;
import com.festival.everyday.core.domain.recruit.ExtraQuestion;
import com.festival.everyday.core.domain.recruit.LaborRecruit;
import com.festival.everyday.core.domain.user.Category;
import com.festival.everyday.core.domain.user.Company;
import com.festival.everyday.core.domain.user.Holder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationExtraQuestionTest {

    Holder holder = Holder.create("H001", "H001", "H001", "1234-5678", "H001@gmail.com",
            Address.create("서울특별시", "마포구", "월드컵로 1길 2"));

    Festival festival = Festival.create(holder, "F001", Period.create(LocalDateTime.MIN, LocalDateTime.MAX), "성인 15,000원", "주말은 19시까지 운영합니다.",
            "염리동 노래왕을 찾아서!", "official.com", "1234-567",
            Address.create("서울특별시", "마포구", "월드컵로 1길 2"));

    Company company = Company.create("C001", "C001", "C001", "1234-5678", "C001@gmail.com",
            Address.create("서울특별시", "마포구", "월드컵로 1길 2"),
            "맛집입니다.", "ig.com", "원종윤", Category.FOOD, "1234a567");

    Period period = Period.create(LocalDateTime.MIN, LocalDateTime.MAX);

    CompanyRecruit companyRecruit = CompanyRecruit.create(period, "공지", "용모단정");

    Application application = Application.create(companyRecruit, company, festival);

    List<String> str = new ArrayList<>(List.of("A", "B", "C"));

    LaborRecruit laborRecruit = LaborRecruit.create(period, "공지", "빵 반죽", "일급 15,000원", "힘들다.");

    List<ExtraQuestion> extraQuestions = ExtraQuestion.createQuestions(laborRecruit, str);

    @Test
    @DisplayName("정상 생성")
    void create() {
        List<ApplicationExtraQuestion> applicationExtraQuestions = ApplicationExtraQuestion.createApplicationExtraQuestions(application, extraQuestions);
        assertNotNull(applicationExtraQuestions);
    }

    @Test
    @DisplayName("application null")
    void create_applicationNull() {
        assertThrows(IllegalArgumentException.class, () -> ApplicationExtraQuestion.createApplicationExtraQuestions(null, extraQuestions));
    }

    @Test
    @DisplayName("extraQuestions null")
    void create_extraQuestionsNull() {
        assertThrows(IllegalArgumentException.class, () -> ApplicationExtraQuestion.createApplicationExtraQuestions(application, null));
    }
}