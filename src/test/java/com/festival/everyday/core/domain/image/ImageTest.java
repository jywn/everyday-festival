package com.festival.everyday.core.domain.image;

import com.festival.everyday.core.domain.common.value.Address;
import com.festival.everyday.core.domain.user.Holder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ImageTest {

    Holder holder = Holder.create("H001", "H001", "H001", "1234-5678", "H001@gmail.com",
            Address.create("서울특별시", "마포구", "월드컵로 1길 2"));


    @Test
    @DisplayName("정상 생성")
    public void create() throws Exception {
        Image image = Image.create("img", OwnerType.HOLDER, 1L);
        assertNotNull(image);
        assertEquals("img", image.getUrl());
        assertEquals(OwnerType.HOLDER, image.getOwnerType());
    }

    @Test
    @DisplayName("url null")
    void create_urlNull() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> Image.create(null, OwnerType.HOLDER, 1L));
    }

    @Test
    @DisplayName("owner null")
    void create_ownerNull() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> Image.create("img", null, 1L));
    }
}