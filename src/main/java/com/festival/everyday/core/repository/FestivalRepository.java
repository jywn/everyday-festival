package com.festival.everyday.core.repository;

import com.festival.everyday.core.domain.Festival;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FestivalRepository extends JpaRepository<Festival,Long> {
    Optional<Festival> findByIdAndHolderId(Long festivalId, Long holderId);
}
