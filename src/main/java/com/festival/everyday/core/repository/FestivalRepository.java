package com.festival.everyday.core.repository;

import com.festival.everyday.core.domain.Festival;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FestivalRepository extends JpaRepository<Festival,Long> {
    Optional<Festival> findByIdAndHolderId(Long festivalId, Long holderId);
    Optional<Long> findLaborRecruitIdById(Long festivalId);
    Optional<Long> findCompanyRecruitIdById(Long festivalId);
    List<Festival> findFestivalsByHolderId(Long holderId);
}
