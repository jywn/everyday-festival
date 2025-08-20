package com.festival.everyday.core.notice.service;

import com.festival.everyday.core.application.domain.Application;
import com.festival.everyday.core.company.domain.Company;
import com.festival.everyday.core.festival.domain.Festival;
import com.festival.everyday.core.interest.domain.Interest;
import com.festival.everyday.core.interest.repository.InterestRepository;
import com.festival.everyday.core.notice.domain.*;
import com.festival.everyday.core.notice.repository.NoticeRepository;
import com.festival.everyday.core.recruit.domain.Recruit;
import com.festival.everyday.core.recruit.dto.RecruitType;
import com.festival.everyday.core.user.domain.Holder;
import com.festival.everyday.core.user.domain.Labor;
import com.festival.everyday.core.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;
    private final InterestRepository interestRepository;

}
