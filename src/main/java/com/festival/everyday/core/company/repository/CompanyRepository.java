package com.festival.everyday.core.company.repository;

import com.festival.everyday.core.company.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company,Long>, CompanyRepositoryCustom {
    List<Long> id(Long id);
}
