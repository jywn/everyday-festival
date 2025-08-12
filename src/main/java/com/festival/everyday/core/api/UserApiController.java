package com.festival.everyday.core.api;

import com.festival.everyday.core.domain.Festival;
import com.festival.everyday.core.dto.FestivalSimpleDto;
import com.festival.everyday.core.dto.response.ApiResponse;
import com.festival.everyday.core.service.FestivalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserApiController {

    private final FestivalService festivalService;

    @GetMapping("/me/festivals")
    public ResponseEntity<ApiResponse<List<FestivalSimpleDto>>> getMyFestivals() {
        Long holderId = 1L;
        List<FestivalSimpleDto> festivalsByHolderId = festivalService.findListByHolderId(holderId);
        return ResponseEntity.ok(ApiResponse.success("내가 등록한 축제 목록을 조회하는데 성공하였습니다.", festivalsByHolderId));
    }
}
