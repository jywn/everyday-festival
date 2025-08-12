package com.festival.everyday.core.dto.request;

import com.festival.everyday.core.domain.user.Gender;
import com.festival.everyday.core.dto.AddressDto;
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
