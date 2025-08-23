package com.festival.everyday.core.common.dummy;

import com.festival.everyday.core.application.service.ApplicationCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(8)
@Component
@RequiredArgsConstructor
public class DummySelectRunner implements CommandLineRunner {

    private final ApplicationCommandService applicationCommandService;

    @Override
    public void run(String... args) throws Exception {

        for (int i = 1; i <= 474; i++) {
            if (i % 7 != 0) {
                if (i % 3 == 0) {
                    long j = (long) i;
                    applicationCommandService.acceptApplication(j);
                } else {
                    long j = (long) i;
                    applicationCommandService.denyApplication(j);
                }
            }
        }
    }
}
