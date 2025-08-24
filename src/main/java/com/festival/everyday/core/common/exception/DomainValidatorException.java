package com.festival.everyday.core.common.exception;

public class DomainValidatorException extends BusinessException {

    protected DomainValidatorException(ErrorCode errorCode) {
        super(ErrorCode.NULL);
    }
}
