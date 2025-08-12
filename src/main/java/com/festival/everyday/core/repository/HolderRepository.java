package com.festival.everyday.core.repository;

import com.festival.everyday.core.domain.user.Holder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HolderRepository extends JpaRepository<Holder,Long> {
    Optional<Holder> findById(Long id);
}
