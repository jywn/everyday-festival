package com.festival.everyday.core.common.dto.command;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CityAndDistrictDto {

    @NotBlank
    private String city;

    @NotBlank
    private String district;
}
