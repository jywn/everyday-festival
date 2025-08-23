package com.festival.everyday.core.image.exception;

import com.festival.everyday.core.common.exception.BusinessException;
import com.festival.everyday.core.common.exception.ErrorCode;

public class FileSaveFailedException extends BusinessException {
    public FileSaveFailedException(Throwable cause) {
        super(ErrorCode.FILE_SAVE_FAILED, cause);
    }
}
