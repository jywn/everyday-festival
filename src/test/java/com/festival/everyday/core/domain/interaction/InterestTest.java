package com.festival.everyday.core.domain.interaction;

import com.festival.everyday.core.festival.domain.Festival;
import com.festival.everyday.core.common.domain.Address;
import com.festival.everyday.core.common.domain.Period;
import com.festival.everyday.core.interest.domain.Interest;
import com.festival.everyday.core.user.domain.Category;
import com.festival.everyday.core.company.domain.Company;
import com.festival.everyday.core.user.domain.Holder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class InterestTest {

    Holder holder = Holder.create("H001", "H001", "H001", "1234-5678", "H001@gmail.com",
            Address.create("서울특별시", "마포구", "월드컵로 1길 2"));

    Festival festival = Festival.create(holder, "F001", Period.create(LocalDateTime.MIN, LocalDateTime.MAX), "성인 15,000원", "주말은 19시까지 운영합니다.",
            "염리동 노래왕을 찾아서!", "official.com", "1234-567",
            Address.create("서울특별시", "마포구", "월드컵로 1길 2"));

    Company company = Company.create("C001", "C001", "C001", "1234-5678", "C001@gmail.com",
            Address.create("서울특별시", "마포구", "월드컵로 1길 2"),
            "맛집입니다.", "ig.com", "원종윤", Category.FOOD, "1234a567");

    @Test
    @DisplayName("정상 생성")
    public void create() throws Exception {
        Interest interest = Interest.create(company, festival);
        assertNotNull(interest);
    }

    @Test
    @DisplayName("festival null")
    void create_festivalNull() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> Interest.create(company, null));
    }

    @Test
    @DisplayName("company null")
    void create_companyNull() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> Interest.create(null, festival));
    }
}