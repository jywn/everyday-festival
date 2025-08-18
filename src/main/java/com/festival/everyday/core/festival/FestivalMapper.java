package com.festival.everyday.core.festival;

import com.festival.everyday.core.common.domain.Address;
import com.festival.everyday.core.common.domain.Period;
import com.festival.everyday.core.festival.domain.Festival;
import com.festival.everyday.core.festival.dto.request.FestivalFormRequest;
import com.festival.everyday.core.user.domain.Holder;
import org.springframework.stereotype.Component;

@Component
public class FestivalMapper {

    // 축제 등록 요청을 축제 엔티티로 변환합니다.
    public static Festival of(Holder holder, FestivalFormRequest request) {
        return Festival.create(holder, request.getName(), Period.create(request.getPeriod().getBegin(), request.getPeriod().getEnd()), request.getFee(), request.getTime(), request.getIntroduction()
                , request.getLink(), request.getTel(), Address.create(request.getAddressDto().getCity(), request.getAddressDto().getDistrict(), request.getAddressDto().getDetail()));
    }
}
