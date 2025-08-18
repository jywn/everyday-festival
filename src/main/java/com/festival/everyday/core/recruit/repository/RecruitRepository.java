package com.festival.everyday.core.recruit.repository;

import com.festival.everyday.core.recruit.domain.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitRepository extends JpaRepository<Recruit, Long> {
}
