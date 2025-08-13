package com.festival.everyday.core.service;

import com.festival.everyday.core.domain.common.value.Address;
import com.festival.everyday.core.domain.user.Company;
import com.festival.everyday.core.domain.user.Holder;
import com.festival.everyday.core.domain.user.Labor;
import com.festival.everyday.core.domain.user.User;
import com.festival.everyday.core.dto.AddressDto;
import com.festival.everyday.core.dto.request.CompanyRegisterRequest;
import com.festival.everyday.core.dto.request.HolderRegisterRequest;
import com.festival.everyday.core.dto.request.LaborRegisterRequest;
import com.festival.everyday.core.repository.HolderRepository;
import com.festival.everyday.core.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class UserService {
    /*
     * userId(PK)를 기준으로 User를 조회한다.
     * SINGLE_TABLE 상속 구조이므로 Holder/Company/Labor 등 하위 타입도 함께 조회된다.
    */
    private final UserRepository userRepository;
    private final HolderRepository holderRepository;
    private final PasswordEncoder passwordEncoder;

    public User findById(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(()-> new EntityNotFoundException("Unexpected User"));
    }

    public Long holderJoin(HolderRegisterRequest request) {

        // 계정 중복을 확인한다.
        if (userRepository.existsByAccount(request.getAccount())) {
            throw new EntityExistsException("중복 계정입니다.");
        }
        // 주소를 DTO 로 변환한다.
        AddressDto addressDto = request.getAddressDto();
        Address address = Address.create(addressDto.getCity(), addressDto.getDistrict(), addressDto.getDetail());
        String encodedPw = passwordEncoder.encode(request.getPassword());
        ;
        // Holder 엔티티를 생성한다.
        Holder holder = Holder.create(request.getAccount(), encodedPw, request.getName(),
                request.getTel(), request.getEmail(), address);

        // 엔티티를 영속화(DB에 저장)한다.
        return userRepository.save(holder).getId();
    }

    public Long companyJoin(CompanyRegisterRequest request) {

        // 계정 중복을 확인한다.
        if (userRepository.existsByAccount(request.getAccount())) {
            throw new EntityExistsException("중복 계정입니다.");
        }
        // 주소를 DTO 로 변환한다.
        AddressDto addressDto = request.getAddressDto();
        Address address = Address.create(addressDto.getCity(), addressDto.getDistrict(), addressDto.getDetail());
        String encodedPw = passwordEncoder.encode(request.getPassword());

        // Company 엔티티를 생성한다.
        Company company = Company.create(request.getAccount(), encodedPw, request.getName(), request.getTel(), request.getEmail(),
                address, request.getIntroduction(), request.getLink(), request.getCeoName(), request.getCategory(), "0");

        // 엔티티를 영속화(DB에 저장)한다.
        return userRepository.save(company).getId();
    }

    public Long laborJoin(LaborRegisterRequest request) {

        // 계정 중복을 확인한다.
        if (userRepository.existsByAccount(request.getAccount())) {
            throw new EntityExistsException("중복 계정입니다.");
        }
        // 주소를 DTO 로 변환한다.
        AddressDto addressDto = request.getAddressDto();
        Address address = Address.create(addressDto.getCity(), addressDto.getDistrict(), addressDto.getDetail());
        String encodedPw = passwordEncoder.encode(request.getPassword());

        // Labor 엔티티를 생성한다.
        Labor labor = Labor.create(request.getAccount(), encodedPw, request.getName(), request.getTel(), request.getEmail(),
                address, request.getAge(), request.getGender());

        // 엔티티를 영속화(DB에 저장)한다.
        return userRepository.save(labor).getId();
    }
}
