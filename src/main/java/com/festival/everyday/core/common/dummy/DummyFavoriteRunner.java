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
        for (int userId = 1; userId <= 100; userId++) {
            Long senderId = (long) userId;

            /**
             *  userId가 짝수면 festivalId 짝수 다 찜 (2,4...,30)
             *  userId가 홀수면 festivalId 홀수 다 찜 (1,3...,29)
             */
            for (int k = 0; k < 15; k++) {
                Long receiverId = (long) (2 * k + (userId % 2 == 0 ? 2 : 1));
                FavoriteRequest request = new FavoriteRequest(receiverId, ReceiverType.FESTIVAL);
                favoriteCommandService.favor(senderId, request);
            }

            /**
             *  userId가 짝수면 CompanyId 짝수 다 찜 (2,4,...,60)
             *  userId가 홀수면 CompanyId 홀수 다 찜 (1,3,...,59)
             */
            for (int k = 1; k <= 30; k++) {
                Long receiverId = (long) (2 * k - (userId % 2 == 0 ? 0 : 1));
                FavoriteRequest request = new FavoriteRequest(receiverId, ReceiverType.COMPANY);
                favoriteCommandService.favor(senderId, request);
            }
        }
    }
}
