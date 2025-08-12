package com.festival.everyday.core.dto.request;

import com.festival.everyday.core.domain.user.Category;
import com.festival.everyday.core.dto.AddressDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CompanyRegisterRequest {

    private String account;
    private String password;
    private String name;
    private String tel;
    private String email;
    private AddressDto addressDto;
    private String introduction;
    String link;
    String ceoName;
    Category category;
}
