package com.festival.everyday.core.image.service;

import com.festival.everyday.core.image.domain.Image;
import com.festival.everyday.core.image.dto.common.ImageDto;
import com.festival.everyday.core.image.exception.FileNotFoundException;
import com.festival.everyday.core.image.exception.FileSaveFailedException;
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

    private final ImageRepository imageRepository;

    /** 파일이 저장될 디렉토리 경로 (예: C:\\images\\ 또는 /var/app/images/) */
    @Value("${IMAGE_URL}")
    private String baseDir;

    public Long upload(ImageDto imageDto, MultipartFile file) {
        // 0) 기본 검증
        if (file == null || file.isEmpty()) {
            throw new FileNotFoundException();
        }
        String originalName = file.getOriginalFilename();
        if (originalName == null || originalName.isBlank()) {
            throw new FileSaveFailedException(new IllegalArgumentException("original filename is blank"));
        }

        // 1) 확장자/베이스네임 분리 (확장자 중복 방지)
        int dot = originalName.lastIndexOf('.');
        if (dot < 0 || dot == originalName.length() - 1) {
            throw new FileSaveFailedException(new IllegalArgumentException("invalid filename: " + originalName));
        }
        String ext  = originalName.substring(dot + 1).toLowerCase();  // png / jpg / jpeg ...
        String base = originalName.substring(0, dot);

        // 2) 저장 디렉토리 보장
        File dir = new File(baseDir);
        if (!dir.exists() && !dir.mkdirs()) {
            throw new FileSaveFailedException(new IOException("failed to create directory: " + dir.getAbsolutePath()));
        }

        // 3) 암호화 파일명 생성 및 최종 파일 경로
        String encodedName = UUID.randomUUID() + "_" + base + "." + ext; // uuid_원본이름.확장자
        File dest = new File(dir, encodedName);

        // 4) 파일 저장
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            throw new FileSaveFailedException(e);
        }

        // 5) 엔티티 생성 (image_url = 전체 경로 저장)
        Image image = Image.create(
                dest.getAbsolutePath(),               // DB에 전체 경로 보관
                imageDto.getOwnerType(),
                imageDto.getOwnerId(),
                originalName,
                encodedName
        );

        // 6) DB 저장 후 id 반환
        return imageRepository.save(image).getId();
    }

    /** 확장자 추출 (소문자) */
    @SuppressWarnings("unused")
    private String extractExt(String name) {
        int pos = name.lastIndexOf('.');
        return name.substring(pos + 1).toLowerCase();
    }
}
