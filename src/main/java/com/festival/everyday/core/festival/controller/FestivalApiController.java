package com.festival.everyday.core.festival.controller;

import com.festival.everyday.core.common.config.jwt.TokenAuthenticationFilter;
import com.festival.everyday.core.user.domain.UserType;
import com.festival.everyday.core.common.dto.response.ApiResponse;
import com.festival.everyday.core.common.dto.response.PageResponse;
import com.festival.everyday.core.company.dto.response.CompanySimpleResponse;
import com.festival.everyday.core.festival.dto.command.FestivalSearchDto;
import com.festival.everyday.core.festival.dto.request.FestivalFormRequest;
import com.festival.everyday.core.festival.dto.response.FestivalDetailResponse;
import com.festival.everyday.core.common.dto.request.SearchRequest;
import com.festival.everyday.core.festival.service.FestivalService;
import com.festival.everyday.core.ai.service.RecommendService;
import com.festival.everyday.core.recruit.service.RecruitService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.festival.everyday.core.user.domain.UserType.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/festivals")
public class FestivalApiController {

    private final FestivalService festivalService;
    private final RecommendService recommendService;
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

    @GetMapping("/{festival_id}/recommended-companies")
    public ResponseEntity<ApiResponse<List<CompanySimpleResponse>>> recommendCompanies(@PathVariable Long festival_id) {
        List<CompanySimpleResponse> result = recommendService.recommendCompany(festival_id).stream().map(CompanySimpleResponse::from).toList();
        return ResponseEntity.ok(ApiResponse.success("추천 업체 목록 조회에 성공했습니다.", result));
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

}
