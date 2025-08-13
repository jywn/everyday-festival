package com.festival.everyday.core.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class MyProfileResponse {
    Long id;
    String userType;
    String userName;

    public MyProfileResponse(Long Id, String userType, String userName) {
        this.id = Id;
        this.userType = userType;
        this.userName = userName;
    }
}
