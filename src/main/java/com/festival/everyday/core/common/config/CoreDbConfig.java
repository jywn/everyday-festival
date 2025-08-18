package com.festival.everyday.core.common.config;

import jakarta.persistence.EntityManagerFactory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {
                "com.festival.everyday.core.application.repository",
                "com.festival.everyday.core.company.repository",
                "com.festival.everyday.core.favorite.repository",
                "com.festival.everyday.core.festival.repository",
                "com.festival.everyday.core.image.repository",
                "com.festival.everyday.core.interest.repository",
                "com.festival.everyday.core.recruit.repository",
                "com.festival.everyday.core.review.repository",
                "com.festival.everyday.core.token.repository",
                "com.festival.everyday.core.user.repository",
        },
        entityManagerFactoryRef = "coreEntityManagerFactory",
        transactionManagerRef = "coreTransactionManager"
)
public class CoreDbConfig {

    /**
     * core DB와 상호작용할 dataSource 를 빈으로 등록합니다.
     */
    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.core")
    public DataSource coreDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 기본 EntityManagerFactory 를 스프링 빈으로 등록합니다.
     */
    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean coreEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("coreDataSource") DataSource dataSource
    ) {
        return builder
                .dataSource(dataSource)
                .packages("com.festival.everyday.core") // Entity 위치
                .persistenceUnit("core")
                .build();
    }

    /**
     * 기본 PlatformTransactionManager 를 스프링 빈으로 등록합니다.
     */
    @Primary
    @Bean
    public PlatformTransactionManager coreTransactionManager(
            @Qualifier("coreEntityManagerFactory") EntityManagerFactory emf
    ) {
        return new JpaTransactionManager(emf);
    }


    /**
     * 코드 절대 수정 금지
     */
}
