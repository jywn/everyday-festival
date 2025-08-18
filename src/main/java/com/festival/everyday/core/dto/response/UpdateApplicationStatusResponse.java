package com.festival.everyday.core.dto.response;

import com.festival.everyday.core.domain.application.SELECTED;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class UpdateApplicationStatusResponse {
    private Long applicationId;
    private SELECTED selected;
}
