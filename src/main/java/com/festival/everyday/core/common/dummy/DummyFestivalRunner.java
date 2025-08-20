package com.festival.everyday.core.common.dummy;

import com.festival.everyday.core.common.domain.Period;
import com.festival.everyday.core.common.dto.command.AddressDto;
import com.festival.everyday.core.common.dto.command.PeriodDto;
import com.festival.everyday.core.festival.dto.request.FestivalFormRequest;
import com.festival.everyday.core.festival.service.FestivalCommandService;
import com.festival.everyday.core.image.service.ImageCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Order(2)
@Component
@RequiredArgsConstructor
public class DummyFestivalRunner implements CommandLineRunner {

    private final FestivalCommandService festivalCommandService;
    private final ImageCommandService imageCommandService;


    @Override
    public void run(String... args) throws Exception {

        // 더미 축제를 저장한다
        for (int i = 0; i < 200; i++) {
            FestivalFormRequest request = new FestivalFormRequest(
                    "F_name_" + i,
                    PeriodDto.of(LocalDateTime.now(), LocalDateTime.now()),
                    AddressDto.of("F_city_" + i, "F_district_" + i, "F_detail_" + i),
                    "F_fee_" + i,
                    "F_time_" + i,
                    "F_intro_" + i,
                    "F_link_" + i,
                    "F_tel_" + i
            );

            // 1인 2축제
            festivalCommandService.save((long) (201 + (i % 100)), request);
        }
    }
}
