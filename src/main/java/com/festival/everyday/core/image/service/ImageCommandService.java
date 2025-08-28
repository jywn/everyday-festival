package com.festival.everyday.core.image.service;

import com.festival.everyday.core.image.domain.Image;
import com.festival.everyday.core.image.dto.common.ImageDto;
import com.festival.everyday.core.image.exception.FileNotFoundException;
import com.festival.everyday.core.image.exception.FileSaveFailedException;
import com.festival.everyday.core.image.exception.InvalidFileNameException;
import com.festival.everyday.core.image.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ImageCommandService {

    @Value("${IMAGE_URL}")
    private String url;

    private final ImageRepository imageRepository;

    public Long upload(ImageDto imageDto, MultipartFile file) {
        String originalName = file.getOriginalFilename();
        if (originalName == null || originalName.isBlank()) {
            throw new InvalidFileNameException();
        }

        String ext = extractExt(originalName);
        String baseName = originalName.substring(0, originalName.lastIndexOf("."));

        // uuid_원본이름.확장자
        String encodedName = UUID.randomUUID() + "_" + baseName + "." + ext;

        // Image 엔티티 생성 (url은 디렉토리 경로까지만 저장됨)
        Image image = Image.create(url + encodedName, imageDto.getOwnerType(), imageDto.getOwnerId(), originalName, encodedName);

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
            throw new FileSaveFailedException(e);
        }

        return imageRepository.save(image).getId();
    }

    /** 확장자 추출 (소문자) */
    @SuppressWarnings("unused")
    private String extractExt(String name) {
        int pos = name.lastIndexOf('.');
        return name.substring(pos + 1).toLowerCase();
    }
}
