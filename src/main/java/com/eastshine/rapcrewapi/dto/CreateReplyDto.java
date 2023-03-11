package com.eastshine.rapcrewapi.dto;

import com.eastshine.rapcrewapi.domain.Article;
import com.eastshine.rapcrewapi.domain.Reply;
import com.eastshine.rapcrewapi.domain.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CreateReplyDto {

    @NotNull
    private Long userId;

    private User user;

    @NotNull
    private Long articleId;

    private Article article;

    private String content;

    private Boolean enabled;

    @Builder
    public CreateReplyDto(Long userId, User user, Long articleId, Article article, String content, Boolean enabled) {
        this.userId = userId;
        this.user = user;
        this.articleId = articleId;
        this.article = article;
        this.content = content;
        this.enabled = enabled;
    }

    public Reply toEntity() {
        return Reply.builder()
                .user(user)
                .article(article)
                .content(content)
                .enabled(enabled)
                .build();
    }


}
