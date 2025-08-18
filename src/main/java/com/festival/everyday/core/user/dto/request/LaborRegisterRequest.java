package com.festival.everyday.core.user.dto.request;

import com.festival.everyday.core.user.domain.Gender;
import com.festival.everyday.core.common.dto.command.AddressDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LaborRegisterRequest {

    private String account;
    private String password;
    private String name;
    private String tel;
    private String email;
    private AddressDto addressDto;
    private Integer age;
    private Gender gender;
}
