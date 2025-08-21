package com.festival.everyday.core.common.dummy;

import com.festival.everyday.core.interest.service.InterestCommandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(4)
@Component
@Slf4j
@RequiredArgsConstructor
public class DummyInterestRunner implements CommandLineRunner {

    private final InterestCommandService interestCommandService;

    @Override
    public void run(String... args) throws Exception {
        for (int festivalId = 1; festivalId <= 60; festivalId++) {
            int[] companyIds;

            if (festivalId <= 30) {
                companyIds = new int[]{
                        wrapCompany(festivalId),
                        wrapCompany(festivalId + 10),
                        wrapCompany(festivalId + 20)
                };
            } else {
                int base = 61 - festivalId;
                companyIds = new int[]{
                        wrapCompany(base),
                        wrapCompany(base - 20),
                        wrapCompany(base - 10)
                };
            }

            for (int companyId : companyIds) {
                log.info("festival {} -> company {}", festivalId, companyId);
                interestCommandService.createInterest(
                        (long) festivalId,
                        (long) companyId
                );
            }
        }
    }

    private int wrapCompany(int id) {
        if (id <= 0) return id + 30;
        if (id > 30) return id - 30;
        return id;
    }
}
