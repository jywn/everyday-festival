package com.festival.everyday.core.image.service;

import com.festival.everyday.core.image.domain.Image;
import com.festival.everyday.core.image.exception.ImageNotFoundException;
import com.festival.everyday.core.image.repository.ImageRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
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

    // 이미지를 조회합니다. (가능한 사용하지 않습니다.)
    public Resource getImage(Long id) throws MalformedURLException {
        Image findImage = imageRepository.findById(id).orElseThrow(ImageNotFoundException::new);

        Path imgPath = Paths.get(findImage.getFullPath(), findImage.getEncodedName());
        return new UrlResource(imgPath.toUri());
    }
}
