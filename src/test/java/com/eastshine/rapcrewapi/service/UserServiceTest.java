package com.eastshine.rapcrewapi.service;

import com.eastshine.rapcrewapi.domain.User;
import com.eastshine.rapcrewapi.dto.CreateUserRequestDto;
import com.eastshine.rapcrewapi.dto.UpdateUserRequestDto;
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

        // when
        userService.join(new CreateUserRequestDto("test1", "1234", "닉네임1", "a@a.com", false, false, true));

        // then
        assertEquals(true, userRepository.findByLoginId("test1").isPresent());
    }

    @Test
    public void 회원정보수정() throws Exception {
        // given
        User user = userRepository.save(new CreateUserRequestDto("test2", "1234", "닉네임1", "b@b.com", false, false, true).toEntity());

        // when
        userService.update(user.getId(), new UpdateUserRequestDto(null, "닉네임2", null, null, null, null));

        // then
        assertEquals("닉네임2", userRepository.findById(user.getId()).get().getNickname());
    }


}