package com.festival.everyday.core.interest;

import com.festival.everyday.core.common.exception.BusinessException;
import com.festival.everyday.core.common.exception.ErrorCode;

public class RedundantInterestException extends BusinessException {
    public RedundantInterestException() {
        super(ErrorCode.REDUNDANT_INTEREST);
    }
}
