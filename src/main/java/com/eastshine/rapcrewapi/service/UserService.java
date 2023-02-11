package com.eastshine.rapcrewapi.service;

import com.eastshine.rapcrewapi.dto.CreateUserRequestDto;
import com.eastshine.rapcrewapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    /**
     * 회원 가입
     */
    @Transactional
    public boolean join(CreateUserRequestDto user) {

        if(userRepository.findByLoginId(user.getLoginId()).isPresent()) {
            return false;
        }

        userRepository.save(user.toEntity());

        return true;
    }

}
