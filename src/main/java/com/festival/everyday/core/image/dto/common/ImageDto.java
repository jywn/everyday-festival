package com.festival.everyday.core.image.dto.common;

import com.festival.everyday.core.image.domain.OwnerType;
import com.festival.everyday.core.image.dto.request.ImageRequest;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ImageDto {

    private Long ownerId;
    private OwnerType ownerType;

    public static ImageDto from(ImageRequest imageRequest) {
        return new ImageDto(imageRequest.getOwnerId(), imageRequest.getOwnerType());
    }
}
