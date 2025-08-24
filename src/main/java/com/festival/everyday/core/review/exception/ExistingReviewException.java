package com.festival.everyday.core.review.exception;

import com.festival.everyday.core.common.exception.BusinessException;
import com.festival.everyday.core.common.exception.ErrorCode;

public class ExistingReviewException extends BusinessException {
    public ExistingReviewException() {
        super(ErrorCode.REDUNDANT_REVIEW);
    }
}
