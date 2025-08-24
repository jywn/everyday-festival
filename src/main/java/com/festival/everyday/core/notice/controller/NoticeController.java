package com.festival.everyday.core.notice.controller;

import com.festival.everyday.core.common.config.jwt.TokenAuthenticationFilter;
import com.festival.everyday.core.common.dto.response.ApiResponse;
import com.festival.everyday.core.common.dto.response.PageResponse;
import com.festival.everyday.core.notice.domain.Notice;
import com.festival.everyday.core.notice.dto.response.NoticeResponse;
import com.festival.everyday.core.notice.service.NoticeQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeQueryService noticeQueryService;

    // 페이징 수정 필요
    @GetMapping("/users/me/notices")
    public ResponseEntity<ApiResponse<PageResponse<NoticeResponse>>> getMyNotices(@RequestAttribute(name= TokenAuthenticationFilter.ATTR_USER_ID) Long userId,
                                                                          Pageable pageable) {

        PageResponse<NoticeResponse> response = PageResponse.from(noticeQueryService.getNotices(userId, pageable).map(NoticeResponse::from));

        return ResponseEntity.ok(ApiResponse.success("알림 목록 조회에 성공하였습니다.", response));

    }
}
