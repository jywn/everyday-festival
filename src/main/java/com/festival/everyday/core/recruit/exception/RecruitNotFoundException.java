package com.festival.everyday.core.recruit.exception;

import com.festival.everyday.core.common.exception.BusinessException;
import com.festival.everyday.core.common.exception.ErrorCode;

public class RecruitNotFoundException extends BusinessException {
    public RecruitNotFoundException() {
        super(ErrorCode.RECRUIT_NOT_FOUND);
    }
}
