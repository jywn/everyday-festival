package com.festival.everyday.core.dto;

import com.festival.everyday.core.domain.common.value.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class AddressDto {
    private String city;
    private String district;
    private String detail;

    private AddressDto(String city, String district, String detail) {
        this.city = city;
        this.district = district;
        this.detail = detail;
    }

    public static AddressDto from(Address address) {
        return new AddressDto(address.getCity(), address.getDistrict(), address.getDetail());
    }
}
