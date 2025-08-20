package com.festival.everyday.core.image.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class ImageResponse {
    private Long id;
    private String originalName;
    private String imageurl;  // 원본 파일명
}

