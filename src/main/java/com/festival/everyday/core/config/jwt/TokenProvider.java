package com.festival.everyday.core.config.jwt;

import com.festival.everyday.core.domain.user.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.util.*;

@RequiredArgsConstructor
@Service
public class TokenProvider {

    private final JwtProperties jwtProperties;
    private Key signingKey;

    @PostConstruct
    void initKey() {
        this.signingKey = Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(User user, Duration expiresIn) {        //토큰에 expiry,현재 시각 주입
        Date now = new Date();
        Date expiry = new Date(now.getTime() + expiresIn.toMillis());
        return makeToken(expiry, user, now);
    }

    private String makeToken(Date expiry, User user, Date now) {        //토큰 만들기
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)           // 1) 헤더: typ=JWT
                .setIssuer(jwtProperties.getIssuer())                   // 2) iss(발급자): 내 issuer
                .setIssuedAt(now)                                       // 3) iat(발급 시각)
                .setExpiration(expiry)                                  // 4) exp(만료 시각)
                .setSubject(user.getAccount())                          // 5) sub: 로그인 했을때 id
                .claim("userId", user.getId())                          // 6) 커스텀 클레임: Users PK (Long)
                .claim("userType", user.getUserType().name())           // 7) 커스텀 클레임: enum을 문자열로
                .signWith(signingKey, SignatureAlgorithm.HS256)         // 8) 서명: HS256 + 내 SecretKey
                .compact();                                             // 9) JWT 생성
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(signingKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) { // 서명/만료/형식 오류 모두 여기로
            return false;
        }
    }

    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token); // claim 파싱 + 토큰 검증

        // userType → ROLE_{userType} 로 매핑
        String ut = claims.get("userType", String.class);
        String roleName = (ut == null) ? "ROLE_USER" : "ROLE_" + ut;

        Set<SimpleGrantedAuthority> authorities =
                Collections.singleton(new SimpleGrantedAuthority(roleName));

        String principal = claims.getSubject();     //account

        return new UsernamePasswordAuthenticationToken(
                new org.springframework.security.core.userdetails.User(principal, "", authorities),
                null,
                authorities
        );
    }

    public String getAccount(String token) {    //그냥 넣어봄
        return getClaims(token).getSubject();
    }

    public Long getUserId(String token) {
        return getClaims(token).get("userId", Long.class);
    }

    public String getUserType(String token) {
        return getClaims(token).get("userType", String.class);
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)//validatetoken을 호출
                .getBody();
    }
}
