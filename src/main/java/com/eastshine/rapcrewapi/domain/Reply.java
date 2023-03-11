package com.eastshine.rapcrewapi.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    private String content;

    @NotNull
    private Boolean enabled;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    private void createTime() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    private void updateTime() {
        this.updatedAt = LocalDateTime.now();
    }

    @Builder
    public Reply(User user, Article article, String content, Boolean enabled) {
        this.user = user;
        this.article = article;
        this.content = content;
        this.enabled = enabled;
    }

}
