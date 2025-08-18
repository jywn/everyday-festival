package com.festival.everyday.core.common.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchRequest {
    private String keyword;
    private Integer page;
    private Integer size;
}
