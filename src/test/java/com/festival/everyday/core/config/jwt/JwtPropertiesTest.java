package com.festival.everyday.core.config.jwt;

import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;

class JwtPropertiesTest {

    private final ApplicationContextRunner ctxRunner =
            new ApplicationContextRunner()
                    .withPropertyValues(
                            "jwt.issuer=everyday.festival",
                            "jwt.secret-key=KWPio$aMNRgJK&Eoo@JNgYIoOY#noOn!"
                    )
                    .withUserConfiguration(TestConfig.class);

    @Configuration
    @EnableConfigurationProperties(JwtProperties.class)
    static class TestConfig {}

    @Test
    void properties_bind() {
        ctxRunner.run(ctx -> {
            JwtProperties props = ctx.getBean(JwtProperties.class);
            assertThat(props.getIssuer()).isEqualTo("everyday.festival");
            assertThat(props.getSecretKey()).isEqualTo("KWPio$aMNRgJK&Eoo@JNgYIoOY#noOn!");
        });
    }
}
