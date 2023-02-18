package com.eastshine.rapcrewapi.dto;

import com.eastshine.rapcrewapi.domain.Article;
import com.eastshine.rapcrewapi.domain.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CreateArticleDto {

    @NotNull
    private Long userId;

    private User user;

    @NotNull
    private String title;

    private String content;

    private String image;

    private LocalDateTime recruitAt;

    private Integer recruitNum;

    private Boolean enabled;

    @Builder
    public CreateArticleDto(Long userId, User user, String title, String content, String image, LocalDateTime recruitAt,
                             Integer recruitNum, Boolean enabled) {
        this.userId = userId;
        this.user = user;
        this.title = title;
        this.content = content;
        this.image = image;
        this.recruitAt = recruitAt;
        this.recruitNum = recruitNum;
        this.enabled = enabled;
    }

    public Article toEntity() {
        return Article.builder()
                .user(user)
                .title(title)
                .content(content)
                .image(image)
                .recruitAt(recruitAt)
                .recruitNum(recruitNum)
                .enabled(enabled)
                .build();

    }

}
