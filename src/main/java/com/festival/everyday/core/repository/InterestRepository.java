package com.festival.everyday.core.repository;


import com.festival.everyday.core.domain.Festival;
import com.festival.everyday.core.domain.interaction.Interest;
import com.festival.everyday.core.domain.user.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestRepository extends JpaRepository<Interest, Long> {

    boolean existsByFestivalAndCompany(Festival festival, Company company);
}
