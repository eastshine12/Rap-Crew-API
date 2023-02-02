package com.eastshine.rapcrewapi.domain;


import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
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
    public void createTime() {
        this.createdAt = LocalDateTime.now();
    }


}
