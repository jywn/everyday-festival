package com.festival.everyday.core.domain.image;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileStore {

    @Value("${IMAGE_URL}")
    private String imageUrl;

    public String getFullPath(String fileName) {
        return imageUrl + fileName;
   }
}
