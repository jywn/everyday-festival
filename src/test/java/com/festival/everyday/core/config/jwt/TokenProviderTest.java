package com.festival.everyday.core.config.jwt;

import com.festival.everyday.core.domain.user.User;
import com.festival.everyday.core.domain.user.UserType;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TokenProviderTest {

    @Mock
    JwtProperties jwtProperties;

    @Mock
    User user;

    TokenProvider tokenProvider;

    private static final String SECRET =
            "KWPio$aMNRgJK&Eoo@JNgYIoOY#noOn!";
    private static final String ISSUER = "festival-everyday-test";

    @BeforeEach
    void setUp() {
        when(jwtProperties.getSecretKey()).thenReturn(SECRET);
        when(jwtProperties.getIssuer()).thenReturn(ISSUER);

        tokenProvider = new TokenProvider(jwtProperties);
        // 같은 패키지라서 package-private initKey() 직접 호출 가능
        tokenProvider.initKey();

        // User mock
        when(user.getId()).thenReturn(42L);
        when(user.getAccount()).thenReturn("geo");
        when(user.getUserType()).thenReturn(UserType.HOLDER); // enum 사용
    }

    @Test
    void generateToken() {
        String token = tokenProvider.generateToken(user, Duration.ofMinutes(5));

        assertNotNull(token);
        assertTrue(tokenProvider.validateToken(token));

        assertEquals("geo", tokenProvider.getAccount(token));
        assertEquals(42L, tokenProvider.getUserId(token));
        assertEquals("HOLDER", tokenProvider.getUserType(token));
    }

    @Test
    void validateToken() {
        // 정상 토큰
        String good = tokenProvider.generateToken(user, Duration.ofMinutes(1));
        assertTrue(tokenProvider.validateToken(good));

        // 만료 토큰(의도적으로 exp를 과거로 설정)
        String expired = Jwts.builder()
                .setIssuer(ISSUER)
                .setSubject("geo")
                .setIssuedAt(new Date(System.currentTimeMillis() - 10_000))
                .setExpiration(new Date(System.currentTimeMillis() - 5_000)) // 이미 만료
                .claim("userId", 42L)
                .claim("userType", "HOLDER")
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
                .compact();
        assertFalse(tokenProvider.validateToken(expired));

        // 서명 불일치 토큰
        String otherSecret = "another_different_secret_key_for_invalid_signature_456";
        String badSig = Jwts.builder()
                .setIssuer(ISSUER)
                .setSubject("geo")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 60_000))
                .claim("userId", 42L)
                .claim("userType", "HOLDER")
                .signWith(Keys.hmacShaKeyFor(otherSecret.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
                .compact();
        assertFalse(tokenProvider.validateToken(badSig));
    }

    @Test
    void getAuthentication() {
        String token = tokenProvider.generateToken(user, Duration.ofMinutes(5));

        Authentication auth = tokenProvider.getAuthentication(token);
        assertNotNull(auth);

        assertEquals("geo", auth.getName());
        assertNull(auth.getCredentials()); // UsernamePasswordAuthenticationToken의 credentials는 null로 설정됨

        // ROLE_{userType} 확인: ROLE_HOLDER
        boolean hasRole = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(a -> a.equals("ROLE_HOLDER"));
        assertTrue(hasRole);
    }

    @Test
    void getAccount() {
        String token = tokenProvider.generateToken(user, Duration.ofMinutes(5));
        assertEquals("geo", tokenProvider.getAccount(token));
    }

    @Test
    void getUserId() {
        String token = tokenProvider.generateToken(user, Duration.ofMinutes(5));
        assertEquals(42L, tokenProvider.getUserId(token));
    }

    @Test
    void getUserType() {
        String token = tokenProvider.generateToken(user, Duration.ofMinutes(5));
        assertEquals("HOLDER", tokenProvider.getUserType(token));
    }
}
