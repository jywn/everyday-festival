package com.festival.everyday.core.api;

import com.festival.everyday.core.domain.Festival;
import com.festival.everyday.core.dto.FestivalSearchDto;
import com.festival.everyday.core.dto.request.FestivalFormRequest;
import com.festival.everyday.core.dto.request.SearchRequest;
import com.festival.everyday.core.dto.response.ApiResponse;
import com.festival.everyday.core.dto.response.FestivalDetailResponse;
import com.festival.everyday.core.dto.response.PageResponse;
import com.festival.everyday.core.service.FestivalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/festivals")
public class FestivalApiController {

    private final FestivalService festivalService;

    @GetMapping("/{festivalId}")
    public ResponseEntity<ApiResponse<FestivalDetailResponse>> getFestival(@PathVariable Long festivalId) {
        Long userId = 1L;//수정 필요
        FestivalDetailResponse festivalDetailResponse = festivalService.findById(userId, festivalId);
        return ResponseEntity.ok(ApiResponse.success("축제 상세 조회에 성공하였습니다.", festivalDetailResponse));
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse<Long>> createFestival(@RequestBody FestivalFormRequest festivalFormRequest) {
        Long holderId = 1L;// 수정 필요
        Long savedId = festivalService.save(holderId, festivalFormRequest);
        return ResponseEntity.ok(ApiResponse.success("축제 등록에 성공하였습니다.", savedId));
    }

    @PostMapping("/search")
    public ResponseEntity<ApiResponse<PageResponse<FestivalSearchDto>>> searchFestivals(@RequestBody SearchRequest searchRequest) {
        PageRequest pageRequest = PageRequest.of(searchRequest.getPage(), searchRequest.getSize());
        ApiResponse<PageResponse<FestivalSearchDto>> success = ApiResponse.success("검색에 성공하였습니다.", festivalService.searchByKeyword(searchRequest.getKeyword(), pageRequest));

        return ResponseEntity.ok(success);
    }
}
