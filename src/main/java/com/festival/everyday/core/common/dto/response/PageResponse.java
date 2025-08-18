package com.festival.everyday.core.common.dto.response;

import lombok.Data;
import org.springframework.data.domain.Page;

@Data
public class PageResponse<T> {

    private int page;             // 현재 페이지 번호 (0-based)
    private int size;             // 페이지당 항목 수
    private long totalElements;   // 전체 항목 수
    private int totalPages;       // 전체 페이지 수
    private boolean first;        // 첫 페이지 여부
    private boolean last;         // 마지막 페이지 여부
    private boolean hasNext;      // 다음 페이지 여부
    private boolean hasPrevious;  // 이전 페이지 여부

    public PageResponse(int page, int size, long totalElements, int totalPages, boolean first, boolean last, boolean hasNext, boolean hasPrevious) {
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.first = first;
        this.last = last;
        this.hasNext = hasNext;
        this.hasPrevious = hasPrevious;
    }

    public static <T> PageResponse<T> from(Page<T> page) {
        return new PageResponse<>(page.getNumber(), page.getSize(), page.getTotalElements(), page.getTotalPages(),
                page.isFirst(), page.isLast(), page.hasNext(), page.hasPrevious());
    }
}

