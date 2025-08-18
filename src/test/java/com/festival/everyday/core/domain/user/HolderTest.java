package com.festival.everyday.core.domain.user;

import com.festival.everyday.core.common.domain.Address;
import com.festival.everyday.core.user.domain.Holder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 도메인의 메서드를 테스트합니다.
 * 도메인 검증 메서드는 null 여부만 확인합니다.
 * */
class HolderTest {

    @Test
    @DisplayName("정상 생성")
    public void create() throws Exception {
        Holder holder = Holder.create("H001", "H001", "H001", "1234-5678", "H001@gmail.com",
                Address.create("서울특별시", "마포구", "월드컵로 1길 2"));

        assertNotNull(holder);
        assertEquals("H001", holder.getAccount());
        assertEquals("월드컵로 1길 2", holder.getAddress().getDetail());
    }

    @Test
    @DisplayName("단일 파라미터 null 이면 예외 발생")
    public void create_singleParameterNull_throwsException() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> {
            Holder.create(null, "H001", "H001", "1234-5678", "H001@gmail.com", Address.create("서울특별시", "마포구", "월드컵로 1길 2"));
        });
    }


    @Test
    @DisplayName("VO null 이면 예외 발생")
    public void create_VoNull_throwsException() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> {
            Holder.create(null, "H001", "H001", "1234-5678", "H001@gmail.com", null);
        });
    }

}