package com.festival.everyday.core.image.controller;

import com.festival.everyday.core.image.domain.OwnerType;
import com.festival.everyday.core.image.dto.common.ImageDto;
import com.festival.everyday.core.image.dto.request.ImageRequest;
import com.festival.everyday.core.common.dto.response.ApiResponse;
import com.festival.everyday.core.image.dto.response.ImageResponse;
import com.festival.everyday.core.image.exception.InvalidOwnerException;
import com.festival.everyday.core.image.service.ImageCommandService;
import com.festival.everyday.core.image.service.ImageQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/images")
public class ImageController {

    private final ImageQueryService imageQueryService;
    private final ImageCommandService imageCommandService;

    @GetMapping
    public ResponseEntity<ApiResponse<ImageResponse>> viewImage(@RequestParam("id") Long id) {
        ImageResponse imageResponse = imageQueryService.getImage(id);
        return ResponseEntity.ok(ApiResponse.success("이미지 조회 성공", imageResponse));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Long>> uploadImage(
            @RequestPart("ownerId") String ownerId,
            @RequestPart("ownerType") String ownerType,
            @RequestPart("image") MultipartFile image) {

        OwnerType type = switch (ownerType) {
            case "FESTIVAL" -> OwnerType.FESTIVAL;
            case "HOLDER" -> OwnerType.HOLDER;
            case "LABOR" -> OwnerType.LABOR;
            case "COMPANY" -> OwnerType.COMPANY;
            default -> throw new InvalidOwnerException();
        };

        ImageRequest imageRequest = new ImageRequest(Long.parseLong(ownerId), type);
        Long id = imageCommandService.upload(ImageDto.from(imageRequest), image);

        return ResponseEntity.ok(ApiResponse.success("이미지 저장에 성공했습니다.", id));
    }
}
