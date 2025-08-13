package com.festival.everyday.core.api;

import com.festival.everyday.core.dto.CompanySearchDto;
import com.festival.everyday.core.dto.request.SearchRequest;
import com.festival.everyday.core.dto.response.ApiResponse;
import com.festival.everyday.core.dto.response.PageResponse;
import com.festival.everyday.core.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
