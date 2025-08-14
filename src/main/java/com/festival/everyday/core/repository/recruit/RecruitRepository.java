package com.festival.everyday.core.repository.recruit;

import com.festival.everyday.core.domain.recruit.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitRepository extends JpaRepository<Recruit, Long> {
}
