package com.festival.everyday.core.api;

import com.festival.everyday.core.config.jwt.TokenAuthenticationFilter;
import com.festival.everyday.core.domain.user.Company;
import com.festival.everyday.core.dto.CompanyDetailDto;
import com.festival.everyday.core.dto.CompanySearchDto;
import com.festival.everyday.core.dto.request.SearchRequest;
import com.festival.everyday.core.dto.response.ApiResponse;
import com.festival.everyday.core.dto.response.PageResponse;
import com.festival.everyday.core.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/companies")
public class CompanyApiController {

    private final CompanyService companyService;

    // 검색 메서드
    @PostMapping("/search")
    public ResponseEntity<ApiResponse<PageResponse<CompanySearchDto>>> searchCompanies(@RequestBody SearchRequest searchRequest) {
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

        return ResponseEntity.ok(ApiResponse.success("업체 상세 정보 조회 성공", companyDetailDto));
    }
}
