package com.eastshine.rapcrewapi.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Scrap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    private void createdTime() {
        this.createdAt = LocalDateTime.now();
    }

    @Builder
    public Scrap(User user, Article article) {
        this.user = user;
        this.article = article;

    }

}
