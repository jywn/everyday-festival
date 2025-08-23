package com.festival.everyday.core.user.exception;

import com.festival.everyday.core.common.exception.BusinessException;
import com.festival.everyday.core.common.exception.ErrorCode;

public class RedundantAccountException extends BusinessException {

    public RedundantAccountException() {
        super(ErrorCode.REDUNDANT_ACCOUNT);
    }
}
