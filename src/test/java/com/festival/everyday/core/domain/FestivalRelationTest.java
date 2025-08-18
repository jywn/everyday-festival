package com.festival.everyday.core.domain;

import com.festival.everyday.core.common.domain.Address;
import com.festival.everyday.core.common.domain.Period;
import com.festival.everyday.core.user.domain.Holder;
import com.festival.everyday.core.festival.domain.Festival;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

class FestivalRelationTest {

    Holder holder = Holder.create("H001", "H001", "H001", "1234-5678", "H001@gmail.com",
            Address.create("서울특별시", "마포구", "월드컵로 1길 2"));

    @Test
    @DisplayName("Holder 가 festival 을 등록합니다.")
    void createWithHolder() {
        Festival festival = Festival.create(holder, "F001", Period.create(LocalDateTime.MIN, LocalDateTime.MAX), "성인 15,000원", "주말은 19시까지 운영합니다.",
                "염리동 노래왕을 찾아서!", "official.com", "1234-567",
                Address.create("서울특별시", "마포구", "월드컵로 1길 2"));

        assertThat(festival.getHolder().getFestivals()).containsExactly(festival);
        assertThat(festival.getHolder()).isSameAs(holder);
    }
}