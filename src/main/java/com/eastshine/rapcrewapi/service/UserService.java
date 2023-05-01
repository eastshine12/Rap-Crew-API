package com.eastshine.rapcrewapi.service;

import com.eastshine.rapcrewapi.domain.User;
import com.eastshine.rapcrewapi.dto.CreateUserRequestDto;
import com.eastshine.rapcrewapi.dto.UpdateUserRequestDto;
import com.eastshine.rapcrewapi.dto.UserDto;
import com.eastshine.rapcrewapi.exception.RapCrewException;
import com.eastshine.rapcrewapi.repository.UserRepository;
import com.eastshine.rapcrewapi.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;


    /**
     * 회원 가입
     */
    @Transactional
    public boolean join(CreateUserRequestDto user) {
        log.info("UserService join");

        if(userRepository.findByLoginId(user.getLoginId()).isPresent()) {
            return false;
        }
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        user.setRole(false);
        user.setEnabled(true);
        userRepository.save(user.toEntity());

        return true;
    }

    public String login(UserDto userDto) {
        log.info("UserService login");

        User user = userRepository.findByLoginId(userDto.getLoginId()).orElseThrow(()
                -> new IllegalArgumentException("해당 계정이 없습니다. loginId = " + userDto.getLoginId()));

        if(!passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            throw new RapCrewException("잘못된 비밀번호 입니다.", HttpStatus.BAD_REQUEST);
        }

        return jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
    }


    /**
     * 회원 정보 수정
     */
    @Transactional
    public boolean update(Long id, UpdateUserRequestDto request) {
        log.info("UserService update");

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


    /**
     * 개별 회원 조회
     */
    public User findUser(String loginId) {
        User user = userRepository.findByLoginId(loginId).get();
        return user;
    }


}
