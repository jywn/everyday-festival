package com.festival.everyday.core.user.dto.request;

import com.festival.everyday.core.user.domain.Category;
import com.festival.everyday.core.common.dto.command.AddressDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanyRegisterRequest {

    private String account;
    private String password;
    private String name;
    private String tel;
    private String email;
    private AddressDto addressDto;
    private String introduction;
    private String link;
    private String ceoName;
    private Category category;
}
