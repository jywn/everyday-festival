package com.festival.everyday.core.repository;

import com.festival.everyday.core.domain.user.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {
}
