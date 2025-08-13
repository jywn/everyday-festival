package com.festival.everyday.core.repository.company;

import com.festival.everyday.core.domain.Festival;
import com.festival.everyday.core.domain.user.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company,Long>, CompanyRepositoryCustom {
}
