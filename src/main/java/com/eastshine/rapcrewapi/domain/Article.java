package com.eastshine.rapcrewapi.domain;


import com.eastshine.rapcrewapi.dto.UpdateArticleRequestDto;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    private String title;

    private String content;

    private String image;

    @Column(name = "recruit_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime recruitAt;

    @Column(name = "recruit_num")
    private Integer recruitNum;

    @NotNull
    private Boolean enabled;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private List<Reply> reply = new ArrayList<>();


    @PrePersist
    private void createTime() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    private void updateTime() {
        this.updatedAt = LocalDateTime.now();
    }

    @Builder
    public Article(User user, String title, String content, String image,
                   LocalDateTime recruitAt, Integer recruitNum, Boolean enabled) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.image = image;
        this.recruitAt = recruitAt;
        this.recruitNum = recruitNum;
        this.enabled = enabled;
    }

    public void updateArticle(UpdateArticleRequestDto request) {
        if (request.getTitle() != null) this.title = request.getTitle();
        if (request.getContent() != null) this.content = request.getContent();
        if (request.getImage() != null) this.image = request.getImage();
        if (request.getRecruitAt() != null) this.recruitAt = request.getRecruitAt();
        if (request.getRecruitNum() != null) this.recruitNum = request.getRecruitNum();
        if (request.getEnabled() != null) this.enabled = request.getEnabled();
    }

    public void deleteArticle() {
        this.enabled = false;
    }

}
