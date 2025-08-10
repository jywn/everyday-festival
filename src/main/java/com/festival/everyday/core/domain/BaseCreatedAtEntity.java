package com.festival.everyday.core.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/*
본 클래스를 상속한 엔티티는 생성되는 시점에 자동으로 생성 시간이 입력됩니다.
 */
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public class BaseCreatedAtEntity {

    @CreatedDate
    @Column(updatable = false, name = "created_at")
    private LocalDateTime createdAt;
}
