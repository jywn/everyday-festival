package com.festival.everyday.core.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * 중복: 409
 * 부재: 404
 */
@Getter
@AllArgsConstructor
public enum ErrorCode {

    REDUNDANT_ACCOUNT("U409", 409, "이미 존재하는 계정입니다."),
    USER_NOT_FOUND("U404", 404, "존재하지 않는 사용자입니다."),
    INVALID_PASSWORD("U401", 401, "비밀번호 오류입니다."),
    INVALID_TOKEN("U401", 401, "잘못된 토큰입니다."),
    EXPIRED_TOKEN("U401", 401, "만료된 토큰입니다."),
    REVOKED_TOKEN("U401", 401, "폐기된 토큰입니다."),
    FESTIVAL_NOT_FOUND("F404", 404, "존재하지 않는 축제입니다."),
    COMPANY_NOT_FOUND("C404", 404, "존재하지 않는 업체입니다."),
    FILE_NOT_FOUND("F404", 404, "존재하지 않는 파일입니다."),
    FILE_SAVE_FAILED("F500", 500, "파일 저장에 실패하였습니다."),
    IMAGE_NOT_FOUND("I404", 404, "존재하지 않는 이미지입니다."),
    INVALID_NAME("F400", 400, "잘못된 파일 이름입니다."),
    INVALID_OWNER("I403", 403, "사진 저장 권한이 없는 사용자입니다."),
    HOLDER_NOT_FOUND("H404", 404, "존재하지 않는 기획자입니다."),
    APPLICATION_NOT_FOUND("A404", 404, "지원 사실이 존재하지 않습니다."),
    RECRUIT_NOT_FOUND("R404", 404, "존재하지 않는 공고입니다."),
    INTERNAL_SERVER_ERROR("S500", 500, "내부 서버 오류입니다."),
    RESOURCE_NOT_FOUND("404", 404, "요청한 리소스를 찾을 수 없습니다."),
    ACCESS_DENIED("403", 403, "접근 권한이 없습니다."),
    NULL("D400", 400, "NULL 값을 입력하였습니다."),
    IMAGE_NOT_ATTACHED("I400", 400, "이미지를 첨부하지 않았습니다."),
    REDUNDANT_INTEREST("I409", 409, "이미 관심을 보낸 상대입니다."),
    REDUNDANT_REVIEW("R409", 409, "이미 리뷰를 작성하였습니다."),
    PG_VECTOR_FAILED("P500", 500, "벡터 변환을 실패하였습니다."),
    INVALID_REVIEWER("R403", 403, "리뷰 작성 권한이 없습니다.");

    private final String code;
    private final int status;
    private final String message;

}
