package com.eastshine.rapcrewapi.service;

import com.eastshine.rapcrewapi.domain.User;
import com.eastshine.rapcrewapi.dto.CreateArticleDto;
import com.eastshine.rapcrewapi.dto.CreateUserRequestDto;
import com.eastshine.rapcrewapi.repository.ArticleRepository;
import com.eastshine.rapcrewapi.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ArticleServiceTest {

    @Autowired
    ArticleService articleService;
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    public void 글작성() throws Exception {

        // given
        User user = userRepository.save(User.builder()
                .loginId("articletest1")
                .password("1234")
                .nickname("닉네임")
                .email("a@a.com")
                .emailAgree(false)
                .role(false)
                .enabled(true)
                .build());

        // when
        long articleId = articleService.write(CreateArticleDto.builder()
                .userId(user.getId())
                .title("제목1")
                .content("내용~~")
                .recruitAt(LocalDateTime.now())
                .recruitNum(3)
                .enabled(true)
                .build());

        // then
        assertEquals("글작성 시 조회가 가능해야 한다.", "제목1", articleRepository.findById(articleId).get().getTitle());
        assertEquals("작성한 회원과 게시글 작성자 ID가 일치해야 한다.", user.getId(), articleRepository.findById(articleId).get().getUser().getId());

    }

}