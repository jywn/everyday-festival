package com.festival.everyday.core.company.repository;

import com.festival.everyday.core.company.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long>, CompanyRepositoryCustom {
}
