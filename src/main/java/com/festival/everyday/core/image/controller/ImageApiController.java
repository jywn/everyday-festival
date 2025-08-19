package com.festival.everyday.core.image.controller;

import com.festival.everyday.core.image.dto.common.ImageDto;
import com.festival.everyday.core.image.dto.request.ImageRequest;
import com.festival.everyday.core.common.dto.response.ApiResponse;
import com.festival.everyday.core.image.service.ImageCommandService;
import com.festival.everyday.core.image.service.ImageQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/images")
public class ImageApiController {

    private final ImageQueryService imageQueryService;
    private final ImageCommandService imageCommandService;

    // 이미지를 단건 조회합니다. 가능한 호출하지 않습니다.
    @GetMapping
    public ResponseEntity<ApiResponse<Resource>> viewImage(@RequestParam("id") Long id) throws MalformedURLException {
        Resource image = imageQueryService.getImage(id);
        return ResponseEntity.ok(ApiResponse.success("이미지 조회에 성공하였습니다.", image));
    }

    // 이미지를 저장합니다.
    @PostMapping
    public ResponseEntity<ApiResponse<Long>> uploadImage(
            @RequestPart("owner") ImageRequest imageRequest,
            @RequestPart("image") MultipartFile image) {

        Long id = imageCommandService.upload(ImageDto.from(imageRequest), image);

        return ResponseEntity.ok(ApiResponse.success("이미지 저장에 성공했습니다.", id));
    }
}
