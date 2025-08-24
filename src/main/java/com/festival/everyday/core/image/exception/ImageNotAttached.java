package com.festival.everyday.core.image.exception;

import com.festival.everyday.core.common.exception.BusinessException;
import com.festival.everyday.core.common.exception.ErrorCode;

public class ImageNotAttached extends BusinessException {
    public ImageNotAttached() {
        super(ErrorCode.IMAGE_NOT_ATTACHED);
    }
}
