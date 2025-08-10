package com.festival.everyday.core.repository;

import com.festival.everyday.core.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByAccount(String account);
}
