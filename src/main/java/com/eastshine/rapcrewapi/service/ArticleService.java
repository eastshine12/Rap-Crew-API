package com.eastshine.rapcrewapi.service;

import com.eastshine.rapcrewapi.domain.Article;
import com.eastshine.rapcrewapi.domain.User;
import com.eastshine.rapcrewapi.dto.CreateArticleDto;
import com.eastshine.rapcrewapi.repository.ArticleRepository;
import com.eastshine.rapcrewapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleService {


    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;


    /**
     * 글 작성
     */
    @Transactional
    public Long write(CreateArticleDto articleDto) {

        User user = userRepository.findById(articleDto.getUserId()).orElseThrow(()
                -> new IllegalArgumentException("해당 계정이 없습니다. id = " + articleDto.getUserId()));
        articleDto.setUser(user);

        Article a = articleRepository.save(articleDto.toEntity());

        return a.getId();
    }




}
