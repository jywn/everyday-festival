package com.festival.everyday.core.notice.controller;

import com.festival.everyday.core.common.config.jwt.TokenAuthenticationFilter;
import com.festival.everyday.core.common.dto.response.ApiResponse;
import com.festival.everyday.core.notice.dto.response.NoticeResponse;
import com.festival.everyday.core.notice.service.NoticeQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeQueryService noticeQueryService;

    @GetMapping("/users/me/notices")
    public ResponseEntity<ApiResponse<List<NoticeResponse>>> getMyNotices(@RequestAttribute(name= TokenAuthenticationFilter.ATTR_USER_ID) Long userId) {

        List<NoticeResponse> response = noticeQueryService.getNotices(userId).stream().map(NoticeResponse::from).toList();

        return ResponseEntity.ok(ApiResponse.success("알림 목록 조회에 성공하였습니다.", response));

    }
}
