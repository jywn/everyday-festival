package com.festival.everyday.core.festival.exception;

import com.festival.everyday.core.common.exception.BusinessException;
import com.festival.everyday.core.common.exception.ErrorCode;

public class FestivalNotFoundException extends BusinessException {
    public FestivalNotFoundException() {
        super(ErrorCode.FESTIVAL_NOT_FOUND);
    }

}
