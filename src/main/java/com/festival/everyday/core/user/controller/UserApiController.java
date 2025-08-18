package com.festival.everyday.core.user.controller;

import com.festival.everyday.core.common.config.jwt.TokenAuthenticationFilter;
import com.festival.everyday.core.festival.dto.command.MyFestivalDto;
import com.festival.everyday.core.festival.dto.response.MyFestivalResponse;
import com.festival.everyday.core.festival.service.FestivalQueryService;
import com.festival.everyday.core.user.domain.User;
import com.festival.everyday.core.festival.dto.command.FestivalSimpleDto;
import com.festival.everyday.core.user.dto.request.CompanyRegisterRequest;
import com.festival.everyday.core.user.dto.request.HolderRegisterRequest;
import com.festival.everyday.core.user.dto.request.LaborRegisterRequest;
import com.festival.everyday.core.common.dto.response.ApiResponse;
import com.festival.everyday.core.user.dto.response.MyProfileResponse;
import com.festival.everyday.core.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserApiController {

    private final FestivalQueryService festivalQueryService;
    private final UserService userService;

    /**
     * 내가 등록한 축제 목록 조회 API
     * @return 내가 등록한 축제 목록
     */
    @GetMapping("/me/festivals")
    public ResponseEntity<ApiResponse<List<MyFestivalResponse>>> getMyFestivals(@RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_ID)Long userId,
                                                                               @RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_TYPE)String userType) {
        List<MyFestivalResponse> response = festivalQueryService.findListByHolderId(userId).stream().map(MyFestivalResponse::from).toList();

        return ResponseEntity.ok(ApiResponse.success("내가 등록한 축제 목록을 조회하는데 성공하였습니다.", response));
    }

    /**
    * 내 프로필 조회 API
     * @return username, userId, userType
     */
    @GetMapping("/me/profile")
    public ResponseEntity<ApiResponse<MyProfileResponse>>  getMyProfile(@RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_ID)Long userId,
                                                                        @RequestAttribute(name = TokenAuthenticationFilter.ATTR_USER_TYPE)String userType)
    {
        User user = userService.findById(userId);
        String userName = user.getName();

        MyProfileResponse myProfileResponse = new MyProfileResponse(userId,userType,userName);
        return ResponseEntity.ok(ApiResponse.success("내 프로필 보기에 성공하였습니다.", myProfileResponse));
    }

    /**
     * 기획자 회원가입 API
     * @return 기획자 ID
     */
    @PostMapping("/holders")
    public ResponseEntity<ApiResponse<Long>> registerHolder(@RequestBody HolderRegisterRequest request) {
        Long holderId = userService.holderJoin(request);

        return ResponseEntity.ok(ApiResponse.success("기획자 가입에 성공하였습니다.", holderId));
    }

    /**
     * 업체 회원가입 API
     * @return 업체 ID
     */
    @PostMapping("/companies")
    public ResponseEntity<ApiResponse<Long>> registerCompany(@RequestBody CompanyRegisterRequest request) {
        Long companyId = userService.companyJoin(request);

        return ResponseEntity.ok(ApiResponse.success("업체 가입에 성공하였습니다.", companyId));
    }

    /**
     * 근로자 회원가입 API
     * @return 근로자 ID
     */
    @PostMapping("/labors")
    public ResponseEntity<ApiResponse<Long>> registerLabor(@RequestBody LaborRegisterRequest request) {
        Long laborId = userService.laborJoin(request);

        return ResponseEntity.ok(ApiResponse.success("근로자 가입에 성공하였습니다.", laborId));
    }
}
