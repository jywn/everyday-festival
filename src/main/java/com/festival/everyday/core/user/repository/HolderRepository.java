package com.festival.everyday.core.user.repository;

import com.festival.everyday.core.user.domain.Holder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HolderRepository extends JpaRepository<Holder,Long> {
    Optional<Holder> findById(Long id);
}
