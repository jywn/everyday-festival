package com.festival.everyday.core.application.dto.response;

import com.festival.everyday.core.application.domain.SELECTED;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class UpdateApplicationStatusResponse {
    private Long applicationId;
    private SELECTED selected;
}
