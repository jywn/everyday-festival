package com.festival.everyday.core.domain.user.authority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

/**
 * 급조한 코드입니다.
 * 인증 개발 담당의 필요에 따라 편하게 수정하셔도 좋습니다.
 */
@Entity
@Getter
public class Token {

    @Id @GeneratedValue
    @Column(name = "token_id")
    private Long Id;

    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "refresh_token")
    private String refreshToken;
}
