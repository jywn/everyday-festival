package com.festival.everyday.core.common.config.jwt;

import com.festival.everyday.core.user.domain.User;
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

    /** RT 생성용: sid 없이 쓰는 토큰 생성 */
    public String generateRefreshToken(User user, Duration expiresIn) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + expiresIn.toMillis());
        return makeToken(expiry, user, now, null);
    }

    /** AT 생성용: RefreshToken.tokenId를 sid로 넣어준다 */
    public String generateAccessToken(User user, Duration expiresIn, Long sid) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + expiresIn.toMillis());
        return makeToken(expiry, user, now, sid);
    }

    private String makeToken(Date expiry, User user, Date now, Long sid) {
        JwtBuilder builder = Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(jwtProperties.getIssuer())
                .setIssuedAt(now)
                .setExpiration(expiry)
                .setSubject(user.getAccount())
                .claim("userId", user.getId())
                .claim("userType", user.getUserType().name());

        if (sid != null) {
            builder.claim("sid", sid);
        }

        return builder
                .signWith(signingKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(signingKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);

        String ut = claims.get("userType", String.class);
        String roleName = (ut == null) ? "ROLE_USER" : "ROLE_" + ut;

        Set<SimpleGrantedAuthority> authorities =
                Collections.singleton(new SimpleGrantedAuthority(roleName));

        String principal = claims.getSubject();

        return new UsernamePasswordAuthenticationToken(
                new org.springframework.security.core.userdetails.User(principal, "", authorities),
                null,
                authorities
        );
    }

    public String getAccount(String token) {
        return getClaims(token).getSubject();
    }

    public Long getUserId(String token) {
        return getNumberAsLong(getClaims(token).get("userId"));
    }

    public String getUserType(String token) {
        return getClaims(token).get("userType", String.class);
    }

    /** AT에 심어둔 sid 읽기(없으면 null) */
    public Long getSessionId(String token) {
        Object v = getClaims(token).get("sid");
        return getNumberAsLong(v);
    }

    private Long getNumberAsLong(Object v) {
        if (v == null) return null;
        if (v instanceof Number n) return n.longValue();
        return Long.valueOf(v.toString());
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
