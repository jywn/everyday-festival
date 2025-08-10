package com.festival.everyday.core.domain.image;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import javax.validation.constraints.NotNull;

/**
 * 이미지 엔티티는 수정이 필요합니다.
 */
@Entity
@Getter
@Table(name ="image")
public class Image {

    /**
     * Image 에서 등록자를 조회할 일은 없으므로, 단방향으로 설게합니다.
     */
    @Id @GeneratedValue
    @Column(name = "image_id")
    private Long id;

    @NotBlank
    @Column(name = "image_url")
    private String url;



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
