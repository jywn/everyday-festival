package com.festival.everyday.core.user.service;

import com.festival.everyday.core.ai.service.EmbeddingService;
import com.festival.everyday.core.common.domain.Address;
import com.festival.everyday.core.common.dto.command.AddressDto;
import com.festival.everyday.core.company.domain.Company;
import com.festival.everyday.core.favorite.repository.FavoriteRepository;
import com.festival.everyday.core.user.domain.Holder;
import com.festival.everyday.core.user.domain.Labor;
import com.festival.everyday.core.user.dto.request.CompanyRegisterRequest;
import com.festival.everyday.core.user.dto.request.HolderRegisterRequest;
import com.festival.everyday.core.user.dto.request.LaborRegisterRequest;
import com.festival.everyday.core.user.exception.RedundantAccountException;
import com.festival.everyday.core.user.repository.HolderRepository;
import com.festival.everyday.core.user.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserCommandService {

    private final UserRepository userRepository;
    private final HolderRepository holderRepository;
    private final PasswordEncoder passwordEncoder;

    public Long holderJoin(HolderRegisterRequest request) {

        // 계정 중복을 확인한다.
        checkRedundant(request.getAccount());

        // 주소를 DTO 로 변환한다.
        AddressDto addressDto = request.getAddress();
        Address address = Address.create(addressDto.getCity(), addressDto.getDistrict(), addressDto.getDetail());

        // 비밀번호를 암호화한다.
        String encodedPw = passwordEncoder.encode(request.getPassword());

        // Holder 엔티티를 생성한다.
        Holder holder = Holder.create(request.getAccount(), encodedPw, request.getName(), request.getTel(), request.getEmail(), address);

        // 엔티티를 영속화(DB에 저장)한다.
        return holderRepository.save(holder).getId();
    }

    public Long companyJoin(CompanyRegisterRequest request) {

        // 계정 중복을 확인한다.
        checkRedundant(request.getAccount());

        // 주소를 DTO 로 변환한다.
        AddressDto addressDto = request.getAddress();
        Address address = Address.create(addressDto.getCity(), addressDto.getDistrict(), addressDto.getDetail());

        // 비밀번호를 암호화한다.
        String encodedPw = passwordEncoder.encode(request.getPassword());

        // Company 엔티티를 생성한다.
        Company company = Company.create(request.getAccount(), encodedPw, request.getName(), request.getTel(), request.getEmail(),
                address, request.getIntroduction(), request.getLink(), request.getCeoName(), request.getCategory(), "0");

        // 엔티티를 영속화(DB에 저장)한다.
        return userRepository.save(company).getId();
    }

    public Long laborJoin(LaborRegisterRequest request) {

        // 계정 중복을 확인한다.
        checkRedundant(request.getAccount());
        // 주소를 DTO 로 변환한다.
        AddressDto addressDto = request.getAddress();
        Address address = Address.create(addressDto.getCity(), addressDto.getDistrict(), addressDto.getDetail());

        // 비밀번호를 암호화한다.
        String encodedPw = passwordEncoder.encode(request.getPassword());

        // Labor 엔티티를 생성한다.
        Labor labor = Labor.create(request.getAccount(), encodedPw, request.getName(), request.getTel(), request.getEmail(),
                address, request.getAge(), request.getGender());

        // 엔티티를 영속화(DB에 저장)한다.
        return userRepository.save(labor).getId();
    }

    private void checkRedundant(String account) {
        if (userRepository.existsByAccount(account)) {
            throw new RedundantAccountException();
        }
    }

}
