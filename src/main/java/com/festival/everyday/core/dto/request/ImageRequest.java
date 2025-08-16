package com.festival.everyday.core.dto.request;

import com.festival.everyday.core.domain.image.OwnerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
public class ImageRequest {
    private Long ownerId;
    private OwnerType ownerType;
}
