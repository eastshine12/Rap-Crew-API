package com.eastshine.rapcrewapi.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UpdateArticleRequestDto {

    private String title, content, image;
    private LocalDateTime recruitAt;
    private Integer recruitNum;
    private Boolean enabled;

    @Builder
    public UpdateArticleRequestDto(String title, String content, String image, LocalDateTime recruitAt,
                                   Integer recruitNum, Boolean enabled) {
        this.title = title;
        this.content = content;
        this.image = image;
        this.recruitAt = recruitAt;
        this.recruitNum = recruitNum;
        this.enabled = enabled;
    }
}
