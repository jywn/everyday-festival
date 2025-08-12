package com.festival.everyday.core.service;

import com.festival.everyday.core.domain.user.User;
import com.festival.everyday.core.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    /*
     * userId(PK)를 기준으로 User를 조회한다.
     * SINGLE_TABLE 상속 구조이므로 Holder/Company/Labor 등 하위 타입도 함께 조회된다.
    */
    private final UserRepository userRepository;
    public User findById(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(()-> new EntityNotFoundException("Unexpected User"));
    }
}
