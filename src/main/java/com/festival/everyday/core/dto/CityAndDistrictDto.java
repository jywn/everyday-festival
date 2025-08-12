package com.festival.everyday.core.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CityAndDistrictDto {

    @NotBlank
    private String city;

    @NotBlank
    private String district;
}
