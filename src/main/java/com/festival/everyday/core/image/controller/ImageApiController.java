package com.festival.everyday.core.image.controller;

import com.festival.everyday.core.image.domain.Image;
import com.festival.everyday.core.image.domain.OwnerType;
import com.festival.everyday.core.image.dto.common.ImageDto;
import com.festival.everyday.core.image.dto.request.ImageRequest;
import com.festival.everyday.core.common.dto.response.ApiResponse;
import com.festival.everyday.core.image.dto.response.ImageResponse;
import com.festival.everyday.core.image.exception.InvalidOwnerException;
import com.festival.everyday.core.image.service.ImageService;
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

    private final ImageService imageService;

    @GetMapping
    public ResponseEntity<ApiResponse<ImageResponse>> viewImage(@RequestParam("id") Long id) {
        ImageResponse imageResponse = imageService.getImage(id);
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
        Long id = imageService.upload(ImageDto.from(imageRequest), ggimage);

        return ResponseEntity.ok(ApiResponse.success("이미지 저장에 성공했습니다.", id));
    }
}
