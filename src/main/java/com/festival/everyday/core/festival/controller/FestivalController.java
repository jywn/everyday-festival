package com.festival.everyday.core.festival.controller;

import com.festival.everyday.core.company.dto.response.RecommendCompanyResponse;
import com.festival.everyday.core.festival.dto.response.FestivalSearchResponse;
import com.festival.everyday.core.festival.service.FestivalCommandService;
import com.festival.everyday.core.festival.service.FestivalQueryService;
import com.festival.everyday.core.common.dto.response.ApiResponse;
import com.festival.everyday.core.common.dto.response.PageResponse;
import com.festival.everyday.core.festival.dto.request.FestivalFormRequest;
import com.festival.everyday.core.festival.dto.response.FestivalDetailResponse;
import com.festival.everyday.core.common.dto.request.SearchRequest;
import com.festival.everyday.core.ai.service.EmbeddingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.festival.everyday.core.common.config.jwt.TokenAuthenticationFilter.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/festivals")
public class FestivalController {

    private final FestivalQueryService festivalQueryService;
    private final FestivalCommandService festivalCommandService;
    private final EmbeddingService embeddingService;

    @GetMapping("/{festivalId}")
    public ResponseEntity<ApiResponse<FestivalDetailResponse>> getFestival(@RequestAttribute(name = ATTR_USER_ID) Long userId,
                                                                           @PathVariable Long festivalId) {
        // 축제 상세 정보를 조회합니다.
        FestivalDetailResponse festivalDetailResponse = festivalQueryService.findById(userId, festivalId);
        return ResponseEntity.ok(ApiResponse.success("축제 상세 조회에 성공하였습니다.", festivalDetailResponse));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Long>> createFestival(@RequestAttribute(name = ATTR_USER_ID) Long userId,
                                                            @RequestBody FestivalFormRequest festivalFormRequest) {
        // 축제를 등록합니다.
        Long savedId = festivalCommandService.save(userId, festivalFormRequest);
        embeddingService.embedFestival(savedId, festivalFormRequest.getIntroduction());

        return ResponseEntity.ok(ApiResponse.success("축제 등록에 성공하였습니다.", savedId));
    }

    @PostMapping("/search")
    public ResponseEntity<ApiResponse<PageResponse<FestivalSearchResponse>>> searchFestivals(@RequestAttribute(name = ATTR_USER_ID) Long userId,
                                                                                        @RequestBody SearchRequest searchRequest) {
        // 리퀘스트로부터 페이지 객체를 생성합니다.
        PageRequest pageRequest = PageRequest.of(searchRequest.getPage(), searchRequest.getSize());

        PageResponse<FestivalSearchResponse> response = PageResponse.from(festivalQueryService.searchByKeyword(userId, searchRequest.getKeyword(), pageRequest).map(FestivalSearchResponse::from));

        return ResponseEntity.ok(ApiResponse.success("축제 검색에 성공하였습니다.", response));
    }

    @GetMapping("/{festivalId}/recommended-companies")
    public ResponseEntity<ApiResponse<List<RecommendCompanyResponse>>> recommendCompanies(@PathVariable Long festivalId) {

        List<Long> idList = embeddingService.recommendCompanies(festivalId);

        List<RecommendCompanyResponse> response = festivalQueryService.getRecommendedCompanies(idList).stream().map(RecommendCompanyResponse::from).toList();

        return ResponseEntity.ok(ApiResponse.success("추천 업체 목록 조회에 성공하였습니다.", response));
    }
}
