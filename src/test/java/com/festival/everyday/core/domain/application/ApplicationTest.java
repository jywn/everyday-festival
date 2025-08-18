package com.festival.everyday.core.domain.application;

import com.festival.everyday.core.domain.Festival;
import com.festival.everyday.core.domain.common.value.Address;
import com.festival.everyday.core.domain.common.value.Period;
import com.festival.everyday.core.domain.recruit.CompanyRecruit;
import com.festival.everyday.core.domain.user.Category;
import com.festival.everyday.core.domain.user.Company;
import com.festival.everyday.core.domain.user.Holder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest {

    Holder holder = Holder.create("H001", "H001", "H001", "1234-5678", "H001@gmail.com",
            Address.create("서울특별시", "마포구", "월드컵로 1길 2"));

    Festival festival = Festival.create(holder, "F001", Period.create(LocalDateTime.MIN, LocalDateTime.MAX), "성인 15,000원", "주말은 19시까지 운영합니다.",
            "염리동 노래왕을 찾아서!", "official.com", "1234-567",
            Address.create("서울특별시", "마포구", "월드컵로 1길 2"));

    Company company = Company.create("C001", "C001", "C001", "1234-5678", "C001@gmail.com",
            Address.create("서울특별시", "마포구", "월드컵로 1길 2"),
            "맛집입니다.", "ig.com", "원종윤", Category.FOOD, "1234a567");
    List<Category> categories = List.of(Category.ART);
    Period period = Period.create(LocalDateTime.MIN, LocalDateTime.MAX);

    CompanyRecruit companyRecruit = CompanyRecruit.create(period, "공지", "용모단정", categories);

    @Test
    @DisplayName("정상 생성")
    void create() {
        Application application = Application.create(companyRecruit, company, festival);
        assertNotNull(application);
    }

    @Test
    @DisplayName("festival null")
    void create_festivalNull() {
        assertThrows(IllegalArgumentException.class, () -> Application.create(companyRecruit, company, null));
    }

    @Test
    @DisplayName("user null")
    void create_userNull() {
        assertThrows(IllegalArgumentException.class, () -> Application.create(companyRecruit, null, festival));
    }

    @Test
    @DisplayName("recruit null")
    void create_recruitNull() {
        assertThrows(IllegalArgumentException.class, () -> Application.create(null, company, festival));
    }
}