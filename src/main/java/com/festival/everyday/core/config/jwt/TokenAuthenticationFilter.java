package com.festival.everyday.core.config.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod; // [ADD]
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    public static final String ATTR_USER_ID = "auth.user_id";
    public static final String ATTR_USER_TYPE = "auth.user_type";
    public static final String ATTR_SESSION_ID = "auth.session_id"; // [ADD] AT에 sid(세션/RT id) 클레임을 쓴다면 전달용

    private final TokenProvider tokenProvider;
    private final static String HEADER_AUTHORIZATION = "Authorization";
    private final static String TOKEN_PREFIX = "Bearer ";

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) { // [ADD] CORS 프리플라이트는 패스
        return HttpMethod.OPTIONS.matches(request.getMethod());
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader(HEADER_AUTHORIZATION);
        String token = getAccessToken(authorizationHeader);

        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        // 토큰 유효성 먼저 검증
        if (!tokenProvider.validateToken(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
            return;
        }

        // 여기서부터는 항상 attribute 세팅
        Long userId = tokenProvider.getUserId(token);
        request.setAttribute(ATTR_USER_ID, userId);
        request.setAttribute(ATTR_USER_TYPE, tokenProvider.getUserType(token));
        Long sid = tokenProvider.getSessionId(token);
        if (sid != null) request.setAttribute(ATTR_SESSION_ID, sid);

        // Authentication은 비어있을 때만 세팅(원하면 항상 갱신해도 됨)
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            Authentication authentication = tokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }


    private String getAccessToken(String authorizationHeader) {
        if (authorizationHeader != null
                && authorizationHeader.regionMatches(true, 0, TOKEN_PREFIX, 0, TOKEN_PREFIX.length())) { // [MOD] 대소문자 무시
            return authorizationHeader.substring(TOKEN_PREFIX.length()).trim(); // [MOD] 앞뒤 공백 제거
        }
        return null;
    }

}
