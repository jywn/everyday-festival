package com.festival.everyday.core.image.domain;

import com.festival.everyday.core.common.domain.BaseCreatedAtEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

import static com.festival.everyday.core.common.DomainValidator.*;

/**
 * 이미지 엔티티는 수정이 필요합니다.
 */
@Entity
@Getter
@Table(name ="image")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Image extends BaseCreatedAtEntity {

    /**
     * Image 에서 등록자를 조회할 일은 없으므로, 단방향으로 설게합니다.
     */
    @Id @GeneratedValue
    @Column(name = "image_id")
    private Long id;

    @NotNull
    @Column(name = "original_name")
    private String originalName;

    @NotNull
    @Column(name = "encoded_name")
    private String encodedName;

    @NotNull
    @Column(name = "image_url", nullable = false)
    private String url;

    @NotNull
    @Column(name = "owner_type", nullable = false)
    private OwnerType ownerType;

    @NotNull
    @Column(name = "owner_id", nullable = false)
    private Long ownerId;

    /**
     * 외부 호출 불가능한 생성자.
     * 정적 팩토리 메서드에서 사용합니다.
     */
    private Image(String url, OwnerType ownerType, Long ownerId, String originalName, String encodedName) {
        this.url = url;
        this.originalName = originalName;
        this.encodedName = encodedName;
        this.ownerType = ownerType;
        this.ownerId = ownerId;
    }

    /**
     * 단일 공통 진입점.
     * 이미지를 생성합니다.
     */
    public static Image create(String url, OwnerType ownerType, Long ownerId, String originalName, String encodedName) {
        notNull("url", url);
        notNull("ownerType", ownerType);
        notNull("ownerId", ownerId);
        notNull("originalName", originalName);
        notNull("encodedName", encodedName);

        return new Image(url, ownerType, ownerId, originalName, encodedName);
    }

    public String getFullPath() {
        return this.url;
    }
    /**
     * ~ 연관 관계 설정 메서드
     * ======================================
     * 비즈니스 메서드 ~
     *
     * 주의 사항
     * 1. 연관관계 설정 메서드는 가능한 수정하지 않습니다. 필요한 경우 다같이 논의한 후 수정합니다.
     * 2. @Setter 는 가능한 선언하지 않습니다.필요한 경우 메서드 단위로 직접 제작합니다.
     * 3. 그럼에도 불구하고, 세터 메서드도 가능한 제작하지 않습니다. 사용 의도가 나타나도록 이름을 작성합니다.
     */
}
