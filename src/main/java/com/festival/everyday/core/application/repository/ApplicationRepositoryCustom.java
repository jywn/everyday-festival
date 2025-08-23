package com.festival.everyday.core.application.repository;

import com.festival.everyday.core.application.domain.Application;
import com.festival.everyday.core.application.domain.SELECTED;
import com.festival.everyday.core.application.dto.command.CompanyApplicationSimpleDto;
import com.festival.everyday.core.application.dto.command.LaborApplicationDetailDto;
import com.festival.everyday.core.application.dto.command.LaborApplicationSimpleDto;
import com.festival.everyday.core.application.dto.command.MyApplicationSimpleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ApplicationRepositoryCustom {
    public Page<CompanyApplicationSimpleDto> findCompanyApplicationList(Long festivalId, Pageable pageable, SELECTED status);
    public Page<LaborApplicationSimpleDto> findLaborApplicationList(Long festivalId, Pageable pageable, SELECTED status);
    public Page<MyApplicationSimpleDto> findMyApplicationList(Long userId, Pageable pageable, SELECTED status);
    public Application findApplicationDetail(Long applicationId);
    public Boolean isApplicationSelected(Long senderId, Long festivalId);
}
