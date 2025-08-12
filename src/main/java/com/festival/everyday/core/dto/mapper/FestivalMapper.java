package com.festival.everyday.core.dto.mapper;

import com.festival.everyday.core.domain.Festival;
import com.festival.everyday.core.domain.common.value.Address;
import com.festival.everyday.core.domain.common.value.Period;
import com.festival.everyday.core.domain.user.Holder;
import com.festival.everyday.core.dto.FestivalDetailDto;
import com.festival.everyday.core.dto.request.FestivalFormRequest;
import org.springframework.stereotype.Component;

@Component
public class FestivalMapper {

    public Festival toEntity(Holder holder, FestivalFormRequest request) {

        Period period = Period.create(request.getPeriod().getBegin(), request.getPeriod().getEnd());
        Address address = Address.create(request.getAddressDto().getCity(), request.getAddressDto().getDistrict(), request.getAddressDto().getDetail());
        return Festival.create(holder, request.getName(), period, request.getFee(), request.getTime(), request.getIntroduction(), request.getLink(), request.getTel(), address);
    }

}
