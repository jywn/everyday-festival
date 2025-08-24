package com.festival.everyday.core.ai.exception;

import com.festival.everyday.core.common.exception.BusinessException;
import com.festival.everyday.core.common.exception.ErrorCode;

public class PgVectorFailedException extends BusinessException {
    public PgVectorFailedException() {
      super(ErrorCode.PG_VECTOR_FAILED);
    }
}
