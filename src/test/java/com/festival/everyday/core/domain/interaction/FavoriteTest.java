package com.festival.everyday.core.domain.interaction;

import com.festival.everyday.core.domain.Festival;
import com.festival.everyday.core.domain.common.value.Address;
import com.festival.everyday.core.domain.common.value.Period;
import com.festival.everyday.core.domain.user.Category;
import com.festival.everyday.core.domain.user.Company;
import com.festival.everyday.core.domain.user.Holder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class FavoriteTest {

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
        Favorite favorite = Favorite.create(company, ReceiverType.FESTIVAL, 1L);
        assertNotNull(favorite);
        assertEquals(1L, favorite.getReceiverId());
        assertEquals(ReceiverType.FESTIVAL, favorite.getReceiverType());
    }

    @Test
    @DisplayName("전송자 null")
    void create_senderNull() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> Favorite.create(null, ReceiverType.FESTIVAL, 1L));
    }

    @Test
    @DisplayName("수신자 null")
    void create_receiverNull() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> Favorite.create(company, null, null));
    }

}