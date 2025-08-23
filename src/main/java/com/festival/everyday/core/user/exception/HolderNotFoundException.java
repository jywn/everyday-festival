package com.festival.everyday.core.user.exception;

import com.festival.everyday.core.common.exception.BusinessException;
import com.festival.everyday.core.common.exception.ErrorCode;

public class HolderNotFoundException extends BusinessException {
    public HolderNotFoundException() {
        super(ErrorCode.HOLDER_NOT_FOUND);
    }
}
