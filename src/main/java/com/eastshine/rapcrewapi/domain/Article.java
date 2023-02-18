package com.eastshine.rapcrewapi.domain;


import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    private String title;

    private String content;

    private String image;

    @Column(name = "recruit_at")
    private LocalDateTime recruitAt;

    @Column(name = "recruit_num")
    private Integer recruitNum;

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
}
