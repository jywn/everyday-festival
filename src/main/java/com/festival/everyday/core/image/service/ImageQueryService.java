package com.festival.everyday.core.image.service;

import com.festival.everyday.core.image.domain.Image;
import com.festival.everyday.core.image.dto.response.ImageResponse;
import com.festival.everyday.core.image.exception.ImageNotFoundException;
import com.festival.everyday.core.image.repository.ImageRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ImageQueryService {

    private final ImageRepository imageRepository;

    @Value("${IMAGE_URL}")
    private String url;

    /**
     * 이미지 조회
     */
    public ImageResponse getImage(Long id) {
        Image image = imageRepository.findById(id)
                .orElseThrow(ImageNotFoundException::new);

        String imageUrl = url + image.getEncodedName();

        return ImageResponse.of(
                image.getId(),
                image.getOriginalName(),
                imageUrl
        );
    }
}
