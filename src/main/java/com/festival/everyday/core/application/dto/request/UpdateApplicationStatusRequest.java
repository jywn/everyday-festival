package com.festival.everyday.core.application.dto.request;

import com.festival.everyday.core.application.domain.SELECTED;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateApplicationStatusRequest {

    private SELECTED selected;
}
