package com.festival.everyday.core.common.dto.command;

import com.festival.everyday.core.common.domain.Address;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressDto {
    private String city;
    private String district;
    private String detail;

    public static AddressDto from(Address address) {
        return new AddressDto(address.getCity(), address.getDistrict(), address.getDetail());
    }

    public static AddressDto of(String city, String district, String detail) {
        return new AddressDto(city, district, detail);
    }
}
