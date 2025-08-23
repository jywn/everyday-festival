package com.festival.everyday.core.image.exception;

import com.festival.everyday.core.common.exception.BusinessException;
import com.festival.everyday.core.common.exception.ErrorCode;

public class InvalidOwnerException extends BusinessException {
    public InvalidOwnerException() {
      super(ErrorCode.INVALID_OWNER);
    }
}
