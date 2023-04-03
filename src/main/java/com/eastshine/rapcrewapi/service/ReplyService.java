package com.eastshine.rapcrewapi.service;

import com.eastshine.rapcrewapi.domain.Article;
import com.eastshine.rapcrewapi.domain.Reply;
import com.eastshine.rapcrewapi.domain.User;
import com.eastshine.rapcrewapi.dto.CreateReplyDto;
import com.eastshine.rapcrewapi.dto.ReplyDto;
import com.eastshine.rapcrewapi.repository.ArticleRepository;
import com.eastshine.rapcrewapi.repository.ReplyQueryRepository;
import com.eastshine.rapcrewapi.repository.ReplyRepository;
import com.eastshine.rapcrewapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final ReplyQueryRepository replyQueryRepository;

    /*
    * 댓글 작성
    */
    @Transactional
    public Long write(CreateReplyDto createReplyDto) {
        User user = userRepository.findById(createReplyDto.getUserId()).orElseThrow(()
                -> new IllegalArgumentException("해당 계정이 없습니다. id = " + createReplyDto.getUserId()));
        createReplyDto.setUser(user);

        Article article = articleRepository.findById(createReplyDto.getArticleId()).orElseThrow(()
                -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + createReplyDto.getArticleId()));
        createReplyDto.setArticle(article);

        Reply r = replyRepository.save(createReplyDto.toEntity());

        return r.getId();
    }


    public List<ReplyDto> getReply(Long articleId) {
        List<ReplyDto> replies = replyQueryRepository.findReply(articleId);
        return replies;
    }


}
