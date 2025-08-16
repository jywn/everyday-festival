package com.festival.everyday.core.api;

import com.festival.everyday.core.dto.ImageDto;
import com.festival.everyday.core.dto.request.ImageRequest;
import com.festival.everyday.core.dto.response.ApiResponse;
import com.festival.everyday.core.service.ImageService;
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
    public ResponseEntity<ApiResponse<Resource>> viewImage(@RequestParam("id") Long id) throws MalformedURLException {
        Resource image = imageService.getImage(id);
        return ResponseEntity.ok(ApiResponse.success("이미지 조회에 성공하였습니다.", image));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Long>> uploadImage(
            @RequestPart("owner") ImageRequest imageRequest,
            @RequestPart("image") MultipartFile image) {

        Long id = imageService.upload(ImageDto.from(imageRequest), image);

        return ResponseEntity.ok(ApiResponse.success("이미지 저장에 성공했습니다.", id));
    }
}
