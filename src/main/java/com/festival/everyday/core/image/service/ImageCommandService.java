package com.festival.everyday.core.image.service;

import com.festival.everyday.core.image.domain.Image;
import com.festival.everyday.core.image.dto.common.ImageDto;
import com.festival.everyday.core.image.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageCommandService {

    private final ImageRepository imageRepository;

    @Value("${IMAGE_URL}")
    private String url;

    public Long upload(ImageDto imageDto, MultipartFile file) {

        // 빈 파일이면 예외를 발생시킵니다.
        if (file.isEmpty()) {
            throw new IllegalArgumentException("파일이 존재하지 않습니다.");
        }

        // 파일의 이름을 저장합니다.
        String originalName = file.getOriginalFilename();

        // 파일의 이름을 암호화합니다.
        String encodedName = UUID.randomUUID().toString() + "_" + originalName + "." + extractExt(originalName);

        // 이미지 엔티티를 생성합니다.
        Image image = Image.create(url, imageDto.getOwnerType(), imageDto.getOwnerId(), originalName, encodedName);

        // 파일을 저장 공간에 저장합니다.
        try {
            file.transferTo(new File(image.getFullPath()));
        } catch (IOException e) {
            throw new RuntimeException("파일 저장에 실패했습니다.", e);
        }

        // 이미지 엔티티를 DB에 저장합니다.
        Image save = imageRepository.save(image);

        return save.getId();
    }

    // 파일 확장자를 추출합니다.
    private String extractExt(String name) {
        int pos = name.lastIndexOf(".");
        return name.substring(pos + 1);
    }

}
