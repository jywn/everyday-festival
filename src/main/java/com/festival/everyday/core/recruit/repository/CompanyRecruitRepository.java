package com.festival.everyday.core.recruit.repository;

import com.festival.everyday.core.recruit.domain.CompanyRecruit;
import com.festival.everyday.core.recruit.domain.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRecruitRepository extends JpaRepository<CompanyRecruit, Long>{
}
