package com.festival.everyday.core.api;

import com.festival.everyday.core.config.jwt.TokenAuthenticationFilter;
import com.festival.everyday.core.domain.Festival;
import com.festival.everyday.core.domain.user.UserType;
import com.festival.everyday.core.dto.CompanyRecruitDto;
import com.festival.everyday.core.dto.FestivalSearchDto;
import com.festival.everyday.core.dto.request.FestivalFormRequest;
import com.festival.everyday.core.dto.request.SearchRequest;
import com.festival.everyday.core.dto.response.*;
import com.festival.everyday.core.service.FestivalService;
import com.festival.everyday.core.service.RecruitService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.festival.everyday.core.domain.user.UserType.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/festivals")
public class FestivalApiController {

    private final FestivalService festivalService;
    private final RecruitService recruitService;

    @GetMapping("/{festivalId}")
    public ResponseEntity<ApiResponse<FestivalDetailResponse>> getFestival(@RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_ID)Long userId,
                                                                           @RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_TYPE)String userType,
                                                                           @PathVariable Long festivalId) {
        FestivalDetailResponse festivalDetailResponse = festivalService.findById(userId, festivalId);
        return ResponseEntity.ok(ApiResponse.success("축제 상세 조회에 성공하였습니다.", festivalDetailResponse));
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse<Long>> createFestival(@RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_ID)Long userId,
                                                            @RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_TYPE)String userType,
                                                            @RequestBody FestivalFormRequest festivalFormRequest) {
        Long savedId = festivalService.save(userId, festivalFormRequest);       //userType is holder
        return ResponseEntity.ok(ApiResponse.success("축제 등록에 성공하였습니다.", savedId));
    }

    @PostMapping("/search")
    public ResponseEntity<ApiResponse<PageResponse<FestivalSearchDto>>> searchFestivals(@RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_ID)Long userId,
                                                                                        @RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_TYPE)String userType,
                                                                                        @RequestBody SearchRequest searchRequest) {
        PageRequest pageRequest = PageRequest.of(searchRequest.getPage(), searchRequest.getSize());
        ApiResponse<PageResponse<FestivalSearchDto>> success = ApiResponse.success("검색에 성공하였습니다.", festivalService.searchByKeyword(searchRequest.getKeyword(), pageRequest));

        return ResponseEntity.ok(success);
    }

    // 기획자인지 확인합니다.
    @GetMapping("/form")
    public ResponseEntity<String> authHolder() {
        UserType userType = HOLDER; // 수정 필요
        if (!userType.equals(HOLDER)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("기획자 권한이 없습니다.");
        }
        return ResponseEntity.ok("기획자 인증에 성공하였습니다.");
    }

    @GetMapping("/{festivalId}/company-recruit")
    public ResponseEntity<ApiResponse<CompanyRecruitResponse>> viewCompanyRecruit(@PathVariable Long festivalId) {
        CompanyRecruitResponse companyRecruitResponse = CompanyRecruitResponse.from(recruitService.findCompanyRecruit(festivalId));
        return ResponseEntity.ok(ApiResponse.success("업체 모집 공고 조회에 성공하였습니다.", companyRecruitResponse));
    }

    @GetMapping("/{festivalId}/labor-recruit")
    public ResponseEntity<ApiResponse<LaborRecruitResponse>> viewLaborRecruit(@PathVariable Long festivalId) {
        LaborRecruitResponse laborRecruitResponse = LaborRecruitResponse.from(recruitService.findLaborRecruit(festivalId));
        return ResponseEntity.ok(ApiResponse.success("근로자 모집 공고 조회에 성공하였습니다.", laborRecruitResponse));
    }

}
