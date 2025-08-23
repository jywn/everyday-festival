package com.festival.everyday.core.token.exception;

import com.festival.everyday.core.common.exception.BusinessException;
import com.festival.everyday.core.common.exception.ErrorCode;

public class RevokeTokenException extends BusinessException {
    public RevokeTokenException() {
        super(ErrorCode.REVOKED_TOKEN);
    }
}
