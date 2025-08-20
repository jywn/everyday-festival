package com.festival.everyday.core.user.dto.response;

import com.festival.everyday.core.user.domain.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public class MyProfileResponse {
    Long id;
    UserType userType;
    String name;

    public static MyProfileResponse of(Long id, UserType userType, String name) {
        return new MyProfileResponse(id, userType, name);
    }
}
