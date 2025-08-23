package com.festival.everyday.core.image.exception;

import com.festival.everyday.core.common.exception.BusinessException;
import com.festival.everyday.core.common.exception.ErrorCode;

public class FileNotFoundException extends BusinessException {
    public FileNotFoundException() {
        super(ErrorCode.FILE_NOT_FOUND);
    }
}
