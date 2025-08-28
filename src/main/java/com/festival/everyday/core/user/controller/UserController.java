package com.festival.everyday.core.user.controller;

import com.festival.everyday.core.ai.service.EmbeddingService;
import com.festival.everyday.core.application.dto.Progress;
import com.festival.everyday.core.common.config.jwt.TokenAuthenticationFilter;
import com.festival.everyday.core.common.dto.response.PageResponse;
import com.festival.everyday.core.company.dto.response.CompanySimpleResponse;
import com.festival.everyday.core.favorite.service.FavoriteQueryService;
import com.festival.everyday.core.festival.dto.response.FestivalSimpleResponse;
import com.festival.everyday.core.festival.dto.response.MyFestivalResponse;
import com.festival.everyday.core.festival.service.FestivalQueryService;
import com.festival.everyday.core.user.domain.User;
import com.festival.everyday.core.user.dto.request.CompanyRegisterRequest;
import com.festival.everyday.core.user.dto.request.HolderRegisterRequest;
import com.festival.everyday.core.user.dto.request.LaborRegisterRequest;
import com.festival.everyday.core.common.dto.response.ApiResponse;
import com.festival.everyday.core.user.dto.response.MyProfileResponse;
import com.festival.everyday.core.user.service.UserCommandService;
import com.festival.everyday.core.user.service.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;
    private final FestivalQueryService festivalQueryService;
    private final FavoriteQueryService favoriteQueryService;
    private final EmbeddingService embeddingService;

    // 내 프로필을 조회합니다.
    @GetMapping("/me/profile")
    public ResponseEntity<ApiResponse<MyProfileResponse>>  getMyProfile(@RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_ID) Long userId) {
        // 사용자를 조회합니다.
        User user = userQueryService.findById(userId);

        // 토큰 정보가 아닌 서버에서 조회한 결과로 반환하도록 수정하였습니다.
        MyProfileResponse myProfileResponse = new MyProfileResponse(user.getId(), user.getUserType(), user.getName());

        return ResponseEntity.ok(ApiResponse.success("내 프로필 보기에 성공하였습니다.", myProfileResponse));
    }

    // 기획자를 등록합니다.
    @PostMapping("/holders")
    public ResponseEntity<ApiResponse<Long>> registerHolder(@RequestBody HolderRegisterRequest request) {
        Long holderId = userCommandService.holderJoin(request);

        return ResponseEntity.ok(ApiResponse.success("기획자 가입에 성공하였습니다.", holderId));
    }

    // 업체를 등록합니다.
    @PostMapping("/companies")
    public ResponseEntity<ApiResponse<Long>> registerCompany(@RequestBody CompanyRegisterRequest request) {
        Long companyId = userCommandService.companyJoin(request);
        embeddingService.embedCompany(companyId, request.getIntroduction());

        return ResponseEntity.ok(ApiResponse.success("업체 가입에 성공하였습니다.", companyId));
    }

    // 근로자를 등록합니다.
    @PostMapping("/labors")
    public ResponseEntity<ApiResponse<Long>> registerLabor(@RequestBody LaborRegisterRequest request) {
        Long laborId = userCommandService.laborJoin(request);

        return ResponseEntity.ok(ApiResponse.success("근로자 가입에 성공하였습니다.", laborId));
    }

    // 내가 찜한 업체 목록을 조회합니다.
    @GetMapping("/me/favorite-companies")
    public ResponseEntity<ApiResponse<PageResponse<CompanySimpleResponse>>> getFavoriteCompanies(
            @RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_ID) Long userId,
            Pageable pageable) {

        PageResponse<CompanySimpleResponse> response = PageResponse.from(favoriteQueryService.getFavoriteCompanyList(userId, pageable).map(CompanySimpleResponse::from));

        return ResponseEntity.ok(ApiResponse.success("찜한 업체 목록 조회 성공", response));
    }

    // 진행중, 종료 여부를 구분합니다.
    @GetMapping("/me/favorite-festivals")
    public ResponseEntity<ApiResponse<PageResponse<FestivalSimpleResponse>>> getFavoriteFestivals(
            @RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_ID) Long userId,
            @RequestParam(name = "progress") Progress progress,
            Pageable pageable) {

        PageResponse<FestivalSimpleResponse> response = PageResponse.from(favoriteQueryService.getFavoriteFestivalList(userId, pageable, progress).map(FestivalSimpleResponse::from));

        return ResponseEntity.ok(ApiResponse.success("찜한 축제 목록 조회 성공", response));
    }

    // 내가 등록한 축제 목록을 조회합니다.
    @GetMapping("/me/festivals")
    public ResponseEntity<ApiResponse<PageResponse<MyFestivalResponse>>> getMyFestivals(@RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_ID) Long userId,
                                                                                        @RequestParam(name = "progress") Progress progress,
                                                                                        Pageable pageable) {

        PageResponse<MyFestivalResponse> response = PageResponse.from(festivalQueryService.findFestivalsByHolder(userId, pageable, progress).map(MyFestivalResponse::from));

        return ResponseEntity.ok(ApiResponse.success("내가 등록한 축제 목록을 조회하는데 성공하였습니다.", response));
    }

}
