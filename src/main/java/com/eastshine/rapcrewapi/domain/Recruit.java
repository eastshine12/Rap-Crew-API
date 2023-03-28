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
public class Recruit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_user_id")
    private User targetUser;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "quit_status", nullable = false)
    private Boolean quitStatus;

    @Column(name = "request_at", nullable = false)
    private LocalDateTime requestAt;

    @Column(name = "join_at")
    private LocalDateTime joinAt;

    @Builder
    public Recruit(Article article, User targetUser, Integer status, Boolean quitStatus, LocalDateTime requestAt, LocalDateTime joinAt) {
        this.article = article;
        this.targetUser = targetUser;
        this.status = status;
        this.quitStatus = quitStatus;
        this.requestAt = requestAt;
        this.joinAt = joinAt;
    }
}
