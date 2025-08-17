package com.festival.everyday.core.dto;

import com.festival.everyday.core.domain.image.Image;
import com.festival.everyday.core.domain.image.OwnerType;
import com.festival.everyday.core.dto.request.ImageRequest;
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
