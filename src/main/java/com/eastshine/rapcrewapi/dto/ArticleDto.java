package com.eastshine.rapcrewapi.dto;

import com.eastshine.rapcrewapi.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {

    private Long id;
    private String title, content, image, nickname;
    private LocalDateTime recruitAt, createdAt;
    private Integer recruitNum;

    public ArticleDto(Article article) {
        id = article.getId();
        title = article.getTitle();
        content = article.getContent();
        image = article.getImage();
        nickname = article.getUser().getNickname();
        recruitAt = article.getRecruitAt();
        createdAt = article.getCreatedAt();
        recruitNum = article.getRecruitNum();
    }
}
