package com.festival.everyday.core.festival.controller;

import com.festival.everyday.core.common.config.jwt.TokenAuthenticationFilter;
import com.festival.everyday.core.festival.service.FestivalCommandService;
import com.festival.everyday.core.festival.service.FestivalQueryService;
import com.festival.everyday.core.user.domain.UserType;
import com.festival.everyday.core.common.dto.response.ApiResponse;
import com.festival.everyday.core.common.dto.response.PageResponse;
import com.festival.everyday.core.company.dto.response.CompanySimpleResponse;
import com.festival.everyday.core.festival.dto.command.FestivalSearchDto;
import com.festival.everyday.core.festival.dto.request.FestivalFormRequest;
import com.festival.everyday.core.festival.dto.response.FestivalDetailResponse;
import com.festival.everyday.core.common.dto.request.SearchRequest;
import com.festival.everyday.core.ai.service.RecommendService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.festival.everyday.core.common.config.jwt.TokenAuthenticationFilter.*;
import static com.festival.everyday.core.user.domain.UserType.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/festivals")
public class FestivalApiController {

    private final FestivalQueryService festivalQueryService;
    private final FestivalCommandService festivalCommandService;
    private final RecommendService recommendService;

    @GetMapping("/{festivalId}")
    public ResponseEntity<ApiResponse<FestivalDetailResponse>> getFestival(@RequestAttribute(name = ATTR_USER_ID) Long userId,
                                                                           @RequestAttribute(name = ATTR_USER_TYPE) String userType,
                                                                           @PathVariable Long festivalId) {
        // 축제 상세 정보를 조회합니다.
        FestivalDetailResponse festivalDetailResponse = festivalQueryService.findById(userId, festivalId);
        return ResponseEntity.ok(ApiResponse.success("축제 상세 조회에 성공하였습니다.", festivalDetailResponse));
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse<Long>> createFestival(@RequestAttribute(name = ATTR_USER_ID) Long userId,
                                                            @RequestAttribute(name = ATTR_USER_TYPE) String userType,
                                                            @RequestBody FestivalFormRequest festivalFormRequest) {
        // 축제를 등록합니다.
        Long savedId = festivalCommandService.save(userId, festivalFormRequest);
        return ResponseEntity.ok(ApiResponse.success("축제 등록에 성공하였습니다.", savedId));
    }

    @PostMapping("/search")
    public ResponseEntity<ApiResponse<PageResponse<FestivalSearchDto>>> searchFestivals(@RequestAttribute(name = ATTR_USER_ID) Long userId,
                                                                                        @RequestAttribute(name = ATTR_USER_TYPE) String userType,
                                                                                        @RequestBody SearchRequest searchRequest) {
        // 리퀘스트로부터 페이지 객체를 생성합니다.
        PageRequest pageRequest = PageRequest.of(searchRequest.getPage(), searchRequest.getSize());

        // 검색어와 페이지를 전달해 검색합니다.
        ApiResponse<PageResponse<FestivalSearchDto>> success = ApiResponse.success("검색에 성공하였습니다.", festivalQueryService.searchByKeyword(1L, searchRequest.getKeyword(), pageRequest));

        return ResponseEntity.ok(success);
    }

    @GetMapping("/{festival_id}/recommended-companies")
    public ResponseEntity<ApiResponse<List<CompanySimpleResponse>>> recommendCompanies(@PathVariable Long festival_id) {

        // 추천 업체 목록을 조회한 결과를 목록으로 반환합니다.
        List<CompanySimpleResponse> result = recommendService.recommendCompany(1L, festival_id).stream().map(CompanySimpleResponse::from).toList();// 수정 필요

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
