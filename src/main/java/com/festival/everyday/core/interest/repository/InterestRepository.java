package com.festival.everyday.core.interest.repository;


import com.festival.everyday.core.festival.domain.Festival;
import com.festival.everyday.core.interest.domain.Interest;
import com.festival.everyday.core.company.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestRepository extends JpaRepository<Interest, Long> {

    boolean existsByFestivalAndCompany(Festival festival, Company company);
    boolean existsByFestivalIdAndCompanyId(Long festivalId, Long companyId);
}
