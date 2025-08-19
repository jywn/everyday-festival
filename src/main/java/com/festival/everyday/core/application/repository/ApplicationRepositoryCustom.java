package com.festival.everyday.core.application.repository;

import com.festival.everyday.core.application.dto.ApplicationDetailDto;
import com.festival.everyday.core.application.dto.command.CompanyApplicationSimpleDto;
import com.festival.everyday.core.application.dto.command.LaborApplicationSimpleDto;
import com.festival.everyday.core.application.dto.command.MyApplicationSimpleDto;

import java.util.List;

public interface ApplicationRepositoryCustom {
    public List<CompanyApplicationSimpleDto> findCompanyApplicationList(Long festivalId);
    public List<LaborApplicationSimpleDto> findLaborApplicationList(Long festivalId);
    public List<MyApplicationSimpleDto> findMyApplicationList(Long userId);
    public ApplicationDetailDto findApplicationDetail(Long festivalId, Long applicationId);
}
