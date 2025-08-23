package com.festival.everyday.core.token.exception;

import com.festival.everyday.core.common.exception.BusinessException;
import com.festival.everyday.core.common.exception.ErrorCode;

public class ExpiredTokenException extends BusinessException {
    public ExpiredTokenException() {
        super(ErrorCode.EXPIRED_TOKEN);
    }
}
