package com.festival.everyday.core.company.controller;

import com.festival.everyday.core.common.config.jwt.TokenAuthenticationFilter;
import com.festival.everyday.core.common.dto.ReceiverType;
import com.festival.everyday.core.company.domain.Company;
import com.festival.everyday.core.company.dto.command.CompanyDetailDto;
import com.festival.everyday.core.company.dto.command.CompanySearchDto;
import com.festival.everyday.core.favorite.dto.FavorStatus;
import com.festival.everyday.core.common.dto.request.SearchRequest;
import com.festival.everyday.core.common.dto.response.ApiResponse;
import com.festival.everyday.core.common.dto.response.PageResponse;
import com.festival.everyday.core.favorite.repository.FavoriteRepository;
import com.festival.everyday.core.company.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/companies")
public class CompanyApiController {

    private final CompanyService companyService;
    private final FavoriteRepository favoriteRepository;
    // 검색 메서드
    @PostMapping("/search")
    public ResponseEntity<ApiResponse<PageResponse<CompanySearchDto>>> searchCompanies(@RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_ID)Long userId,
                                                                                       @RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_TYPE)String userType,@RequestBody SearchRequest searchRequest) {
        PageRequest pageRequest = PageRequest.of(searchRequest.getPage(), searchRequest.getSize());
        ApiResponse<PageResponse<CompanySearchDto>> success = ApiResponse.success("검색에 성공하였습니다.", companyService.searchByKeyword(searchRequest.getKeyword(), pageRequest));

        return ResponseEntity.ok(success);
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<ApiResponse<CompanyDetailDto>> getCompanyDetail(@RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_ID)Long userId,
                                                                          @RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_TYPE)String userType,
                                                                          @PathVariable Long companyId) {

        Company company = companyService.findById(companyId);
        CompanyDetailDto companyDetailDto = CompanyDetailDto.from(company);
        boolean exists = favoriteRepository.existsBySenderIdAndReceiverIdAndReceiverType(       //찜 확인
                userId, companyId, ReceiverType.COMPANY);
        companyDetailDto.setFavorStatus(exists ? FavorStatus.FAVORED : FavorStatus.NOT_FAVORED); // 업체 상세에 찜 여부 등록

        return ResponseEntity.ok(ApiResponse.success("업체 상세 정보 조회 성공", companyDetailDto));
    }
}
