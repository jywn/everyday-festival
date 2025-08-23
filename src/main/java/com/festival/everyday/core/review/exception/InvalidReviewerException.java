package com.festival.everyday.core.review.exception;

import com.festival.everyday.core.common.exception.BusinessException;
import com.festival.everyday.core.common.exception.ErrorCode;

public class InvalidReviewerException extends BusinessException {
    public InvalidReviewerException() {
        super(ErrorCode.INVALID_REVIEWER);
    }

}
