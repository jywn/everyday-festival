package com.festival.everyday.core.company.exception;

import com.festival.everyday.core.common.exception.BusinessException;
import com.festival.everyday.core.common.exception.ErrorCode;

public class CompanyNotFoundException extends BusinessException {
    public CompanyNotFoundException() {
      super(ErrorCode.COMPANY_NOT_FOUND);
    }
}
