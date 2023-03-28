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
public class Noti {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User receiver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_user_id")
    private User sender;

    private Integer type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_article_id")
    private Article article;

    @Column(name = "is_read")
    private Boolean isRead;

    private Boolean enabled;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void createdTime() {
        this.createdAt = LocalDateTime.now();
    }

    @Builder
    public Noti(User receiver, User sender, Integer type, Article article, Boolean isRead, Boolean enabled) {
        this.receiver = receiver;
        this.sender = sender;
        this.type = type;
        this.article = article;
        this.isRead = isRead;
        this.enabled = enabled;
    }
}
