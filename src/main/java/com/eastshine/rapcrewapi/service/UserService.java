package com.eastshine.rapcrewapi.service;

import com.eastshine.rapcrewapi.domain.User;
import com.eastshine.rapcrewapi.dto.CreateUserRequestDto;
import com.eastshine.rapcrewapi.dto.UpdateUserRequestDto;
import com.eastshine.rapcrewapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    /**
     * 회원 정보 수정
     */
    @Transactional
    public boolean update(Long id, UpdateUserRequestDto request) {

        User user = userRepository.findById(id).orElseThrow(()
                    -> new IllegalArgumentException("해당 계정이 없습니다. id = " + id));

        if(user.getId() == null) {
            return false;
        }

        user.updateUser(request);
        userRepository.save(user);

        return true;
    }

    /**
     * 전체 회원 조회
     */
    public List<User> findUsers() {
        return userRepository.findAll();
    }

}
