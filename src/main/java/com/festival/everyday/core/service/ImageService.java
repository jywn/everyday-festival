package com.festival.everyday.core.service;

import com.festival.everyday.core.domain.image.Image;
import com.festival.everyday.core.dto.ImageDto;
import com.festival.everyday.core.repository.ImageRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    @Value("${IMAGE_URL}")
    private String url;

    public Resource getImage(Long id) throws MalformedURLException {
        Optional<Image> findImage = imageRepository.findById(id);
        if (findImage.isEmpty()) {
            throw new EntityNotFoundException("이미지를 찾을 수 없습니다.");
        }

        Path imgPath = Paths.get(findImage.get().getFullPath(), findImage.get().getEncodedName());
        return new UrlResource(imgPath.toUri());
    }

    public Long upload(ImageDto imageDto, MultipartFile file) {

        String originalName = file.getOriginalFilename();
        String encodedName = UUID.randomUUID().toString() + "_" + originalName + "." + extractExt(originalName);
        Image image = Image.create(url, imageDto.getOwnerType(), imageDto.getOwnerId(), originalName, encodedName);

        try {
            file.transferTo(new File(image.getFullPath()));
        } catch (IOException e) {
            throw new RuntimeException("파일 저장에 실패했습니다.", e);
        }

        Image save = imageRepository.save(image);
        return save.getId();
    }

    public String extractExt(String name) {
        int pos = name.lastIndexOf(".");
        return name.substring(pos + 1);
    }
}
