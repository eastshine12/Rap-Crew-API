package com.eastshine.rapcrewapi.service;

import com.eastshine.rapcrewapi.domain.Article;
import com.eastshine.rapcrewapi.domain.User;
import com.eastshine.rapcrewapi.dto.ArticleDto;
import com.eastshine.rapcrewapi.dto.CreateArticleDto;
import com.eastshine.rapcrewapi.repository.ArticleQueryRepository;
import com.eastshine.rapcrewapi.repository.ArticleRepository;
import com.eastshine.rapcrewapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleService {


    private final ArticleRepository articleRepository;
    private final ArticleQueryRepository articleQueryRepository;
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

    public List<ArticleDto> getArticleAll() {
        List<Article> articles = articleQueryRepository.findArticle();
        List<ArticleDto> result = articles.stream().map(a -> new ArticleDto(a)).collect(Collectors.toList());
        return result;
    }


}
