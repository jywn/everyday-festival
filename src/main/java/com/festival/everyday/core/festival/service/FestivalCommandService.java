package com.festival.everyday.core.festival.service;

import com.festival.everyday.core.festival.FestivalMapper;
import com.festival.everyday.core.festival.domain.Festival;
import com.festival.everyday.core.festival.dto.request.FestivalFormRequest;
import com.festival.everyday.core.festival.repository.FestivalRepository;
import com.festival.everyday.core.user.domain.Holder;
import com.festival.everyday.core.user.exception.HolderNotFoundException;
import com.festival.everyday.core.user.repository.HolderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FestivalCommandService {

    private final HolderRepository holderRepository;
    private final FestivalRepository festivalRepository;


    // 축제를 등록합니다.
    public Long save(Long holderId, FestivalFormRequest festivalFormRequest) {
        // 기획자를 탐색합니다.
        Holder holder = holderRepository.findById(holderId).orElseThrow(HolderNotFoundException::new);

        // 기획자 ID 와 함께 축제를 등록합니다.
        Festival festival = festivalRepository.save(FestivalMapper.of(holder, festivalFormRequest));

        return festival.getId();
    }
}
