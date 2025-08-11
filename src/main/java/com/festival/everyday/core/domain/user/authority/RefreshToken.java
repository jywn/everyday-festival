package com.festival.everyday.core.domain.user.authority;

import com.festival.everyday.core.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "refresh_token")
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //token_id 값
    private Long tokenId;

    @Column(name = "user_id", nullable = false)             //User의 user_id
    private Long userId;

//    @Column(name = "token_hash", nullable = false, length = 128)
//    private String tokenHash; // Refresh Token 해시 값

    @Column(name = "refresh_token", nullable = false, length = 300)
    private String refreshToken;

    @Column(name = "issued_at", nullable = false)       //언제 만들어졌는지
    private Instant issuedAt;

    @Column(name = "expires_at", nullable = false)      //유효기간이 언제인지
    private Instant expiresAt;

    @Column(name = "revoked", nullable = false)         //로그아웃을 하거나 할 시, 토큰이 탈취 당했을 경향이 있을 시 삭제를 위한 용도
    private boolean revoked = false;

    public RefreshToken(Long userId, String refresh_token, Instant issuedAt, Instant expiresAt) {
        this.userId = userId;
        this.refreshToken = refresh_token;
        this.issuedAt = issuedAt;
        this.expiresAt = expiresAt;
    }

    public void revoke() {
        this.revoked = true;
    }
}
