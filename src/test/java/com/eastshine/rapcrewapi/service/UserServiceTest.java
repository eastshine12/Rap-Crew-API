package com.eastshine.rapcrewapi.service;

import com.eastshine.rapcrewapi.domain.User;
import com.eastshine.rapcrewapi.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired UserService userService;
    @Autowired UserRepository userRepository;

    @Test
    public void 회원가입() throws Exception {
        // given
        User user = new User();
        user.setLoginId("test1");
        user.setPassword("1234");
        user.setNickname("닉네임1");
        user.setEmail("a@a.com");
        user.setEmailAgree(false);
        user.setRole(false);
        user.setEnabled(true);

        // when
        Long saveId = userService.join(user);

        // then
        assertEquals(user, userRepository.findById(saveId).get());
    }

}