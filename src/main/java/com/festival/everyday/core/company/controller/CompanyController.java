package com.festival.everyday.core.company.controller;

import com.festival.everyday.core.ai.service.EmbeddingService;
import com.festival.everyday.core.common.config.jwt.TokenAuthenticationFilter;
import com.festival.everyday.core.company.dto.command.CompanySearchDto;
import com.festival.everyday.core.company.dto.response.CompanyDetailResponse;
import com.festival.everyday.core.common.dto.request.SearchRequest;
import com.festival.everyday.core.common.dto.response.ApiResponse;
import com.festival.everyday.core.common.dto.response.PageResponse;
import com.festival.everyday.core.company.service.CompanyQueryService;
import com.festival.everyday.core.festival.dto.response.RecommendFestivalResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyQueryService companyQueryService;
    private final EmbeddingService embeddingService;

    // 업체 검색 메서드
    @PostMapping("/search")
    public ResponseEntity<ApiResponse<PageResponse<CompanySearchDto>>> searchCompanies(@RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_ID)Long userId,
                                                                                       @RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_TYPE)String userType,
                                                                                       @RequestBody SearchRequest searchRequest) {
        // 리퀘스트를 바탕으로 페이지 객체를 생성합니다.
        PageRequest pageRequest = PageRequest.of(searchRequest.getPage(), searchRequest.getSize());

        // 업체 목록을 검색하여 반환합니다.
        ApiResponse<PageResponse<CompanySearchDto>> success = ApiResponse.success("검색에 성공하였습니다.", companyQueryService.searchByKeyword(userId, searchRequest.getKeyword(), pageRequest));

        return ResponseEntity.ok(success);
    }

    // 업체를 상세 조회합니다.
    @GetMapping("/{companyId}")
    public ResponseEntity<ApiResponse<CompanyDetailResponse>> getCompanyDetail(@RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_ID)Long userId,
                                                                          @RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_TYPE)String userType,
                                                                          @PathVariable Long companyId) {

        CompanyDetailResponse result = CompanyDetailResponse.from(companyQueryService.findById(userId, companyId));

        return ResponseEntity.ok(ApiResponse.success("업체 상세 정보 조회 성공", result));
    }

    @GetMapping("/{companyId}/recommended-festivals")
    public ResponseEntity<ApiResponse<List<RecommendFestivalResponse>>> recommendFestivals(@PathVariable Long companyId) {
        List<Long> idList = embeddingService.recommendFestivals(companyId);
        List<RecommendFestivalResponse> response = companyQueryService.getRecommendedFestivals(idList).stream().map(RecommendFestivalResponse::from).collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success("추천 축제 목록 조회에 성공하였습니다.", response));
    }
}
