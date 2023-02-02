package com.eastshine.rapcrewapi.service;


import com.eastshine.rapcrewapi.domain.User;
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
    public Long join(User user) {
        userRepository.save(user);
        return user.getId();
    }

}
