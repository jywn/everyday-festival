package com.festival.everyday.core.festival.repository;

import com.festival.everyday.core.festival.dto.command.FestivalSearchDto;
import com.festival.everyday.core.festival.dto.command.FestivalSimpleDto;
import com.festival.everyday.core.festival.dto.command.MyFestivalDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FestivalRepositoryCustom {
    Page<FestivalSearchDto> dynamicSearch(Long userId, String keyword, Pageable pageable);

    List<MyFestivalDto> findFestivalsByHolderIdWithUrl(Long holderId);
}
