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
public class Likey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    /*
    게시글 1 / 댓글 2 / 유저 3
    */
    private Integer type;

    @Column(name = "target_id")
    private Long targetId;

    private Boolean enabled;

    @Column(name = "created_at")
    private LocalDateTime createdAt;


    @PrePersist
    public void createdTime() {
        this.createdAt = LocalDateTime.now();
    }

    @Builder
    public Likey(User user, Integer type, Long targetId, Boolean enabled) {
        this.user = user;
        this.type = type;
        this.targetId = targetId;
        this.enabled = enabled;
    }

}
