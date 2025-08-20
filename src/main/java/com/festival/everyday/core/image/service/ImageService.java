package com.festival.everyday.core.image.service;

import com.festival.everyday.core.image.domain.Image;
import com.festival.everyday.core.image.dto.common.ImageDto;
import com.festival.everyday.core.image.dto.response.ImageResponse;
import com.festival.everyday.core.image.repository.ImageRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    // 이미지 저장 디렉토리 (application.properties에 IMAGE_URL 설정)
    @Value("${IMAGE_URL}")
    private String url;

    /**
     * 이미지 조회
     */
    public ImageResponse getImage(Long id) {
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("이미지를 찾을 수 없습니다."));

        String imageUrl = url + image.getEncodedName();

        return ImageResponse.of(
                image.getId(),
                image.getOriginalName(),
                imageUrl
        );
    }

    /**
     * 이미지 업로드
     */
    public Long upload(ImageDto imageDto, MultipartFile file) {
        String originalName = file.getOriginalFilename();
        if (originalName == null || originalName.isBlank()) {
            throw new RuntimeException("파일 이름이 유효하지 않습니다.");
        }

        String ext = extractExt(originalName);
        String baseName = originalName.substring(0, originalName.lastIndexOf("."));

        // uuid_원본이름.확장자
        String encodedName = UUID.randomUUID().toString() + "_" + baseName + "." + ext;

        // Image 엔티티 생성 (url은 디렉토리 경로까지만 저장됨)
        Image image = Image.create(url, imageDto.getOwnerType(), imageDto.getOwnerId(), originalName, encodedName);

        try {
            // 디렉토리 생성 보장
            File dir = new File(url);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // 최종 파일 경로 = 디렉토리 + 파일명
            File dest = new File(dir, encodedName);
            file.transferTo(dest);

        } catch (IOException e) {
            throw new RuntimeException("파일 저장에 실패했습니다.", e);
        }

        return imageRepository.save(image).getId();
    }

    /**
     * 확장자 추출
     */
    public String extractExt(String name) {
        int pos = name.lastIndexOf(".");
        return name.substring(pos + 1);
    }
}
