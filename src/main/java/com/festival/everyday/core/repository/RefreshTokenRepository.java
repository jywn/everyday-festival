package com.festival.everyday.core.repository;

import com.festival.everyday.core.domain.user.authority.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
 Optional<RefreshToken> findByUserId(Long userId);      //특정 사용자의 RefreshToken을 찾을 때
 Optional<RefreshToken> findByRefreshToken(String refreshToken);    //특정 RefreshToken이 DB에 저장이 되어있는지 찾을 때
}
