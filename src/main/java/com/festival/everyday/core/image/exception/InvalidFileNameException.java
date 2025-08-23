package com.festival.everyday.core.image.exception;

import com.festival.everyday.core.common.exception.BusinessException;
import com.festival.everyday.core.common.exception.ErrorCode;

public class InvalidFileNameException extends BusinessException {
    public InvalidFileNameException() {
      super(ErrorCode.INVALID_NAME);
    }
}
