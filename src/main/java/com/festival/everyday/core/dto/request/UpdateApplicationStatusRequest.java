package com.festival.everyday.core.dto.request;

import com.festival.everyday.core.domain.application.SELECTED;
import lombok.AllArgsConstructor;
import lombok.Getter;

//지원서 수락/거절/중립 표현하기
@Getter
@AllArgsConstructor
public class UpdateApplicationStatusRequest {
    private SELECTED selected;
}
