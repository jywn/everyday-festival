package com.festival.everyday.core.common.dummy;

import com.festival.everyday.core.interest.service.InterestCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(4)
@Component
@RequiredArgsConstructor
public class DummyInterestRunner implements CommandLineRunner {

    private final InterestCommandService interestCommandService;

    /**
     * festivalId가 1이면 companyId 1,11,21,31,41,51
     * festivalId가 40이면 companyId 10,20,30,40,50,60
     */
    @Override
    public void run(String... args) throws Exception {
        for (int festivalId = 1; festivalId <= 29; festivalId++) {
            int base = (festivalId % 10 == 0) ? 10 : festivalId % 10;

            for (int offset = 0; offset < 6; offset++) {
                int companyId = base + offset * 10;
                interestCommandService.createInterest(
                        (long) festivalId,
                        (long) companyId
                );
            }
        }
    }
}
