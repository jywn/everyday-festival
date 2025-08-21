package com.festival.everyday.core.common.dummy;

import com.festival.everyday.core.common.dto.ReceiverType;
import com.festival.everyday.core.favorite.dto.request.FavoriteRequest;
import com.festival.everyday.core.favorite.service.FavoriteCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(3)
@Component
@RequiredArgsConstructor
public class DummyFavoriteRunner implements CommandLineRunner {

    private final FavoriteCommandService favoriteCommandService;

    private int modNonZero(int value, int mod) {
        int result = value % mod;
        return result == 0 ? mod : result;
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 1; i <= 90; i++) {
            Long senderId = (long) i;

            // FESTIVAL (6개 찜)
            for (int k = 0; k < 6; k++) {
                Long receiverId = (long) modNonZero(i + k * 10, 60);
                FavoriteRequest request = new FavoriteRequest(receiverId, ReceiverType.FESTIVAL);
                favoriteCommandService.favor(senderId, request);
            }

            // COMPANY (3개 찜)
            for (int k = 0; k < 3; k++) {
                Long receiverId = (long) modNonZero(i + k * 10, 30);
                FavoriteRequest request = new FavoriteRequest(receiverId, ReceiverType.COMPANY);
                favoriteCommandService.favor(senderId, request);
            }
        }
    }
}
