package com.festival.everyday.core.image.dto.request;

import com.festival.everyday.core.image.domain.OwnerType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ImageRequest {
    private Long ownerId;
    private OwnerType ownerType;
}
