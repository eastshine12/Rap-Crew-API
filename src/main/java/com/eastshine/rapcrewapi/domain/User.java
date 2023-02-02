package com.eastshine.rapcrewapi.domain;


import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login_id", unique = true)
    @NotNull
    private String loginId;

    @NotNull
    private String password;

    private String nickname;

    private String email;

    @Column(name = "email_agree")
    private boolean emailAgree;

    @Column(name = "last_login_time")
    private LocalDateTime lastLoginTime;

    private boolean role; // 회원 - 0, 관리자 - 1

    private boolean enabled;

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
    public User(String loginId, String password, String nickname, String email, boolean emailAgree, boolean role, boolean enabled) {
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.emailAgree = emailAgree;
        this.role = role;
        this.enabled = enabled;
    }


}
