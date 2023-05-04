package com.eastshine.rapcrewapi.domain;


import com.eastshine.rapcrewapi.dto.UpdateUserRequestDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User implements UserDetails {

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
    private Boolean emailAgree;

    @Column(name = "last_login_time")
    private LocalDateTime lastLoginTime;

    private Boolean role; // 회원 - 0, 관리자 - 1

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
    public User(String loginId, String password, String nickname, String email, Boolean emailAgree, Boolean role, Boolean enabled) {
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.emailAgree = emailAgree;
        this.role = role;
        this.enabled = enabled;
    }

    public void updateUser(UpdateUserRequestDto request) {
        if (request.getPassword() != null) this.password = request.getPassword();
        if (request.getNickname() != null) this.nickname = request.getNickname();
        if (request.getEmail() != null) this.email = request.getEmail();
        if (request.getEmailAgree() != null) this.emailAgree = request.getEmailAgree();
        if (request.getRole() != null) this.role = request.getRole();
        if (request.getEnabled() != null) this.enabled = request.getEnabled();
    }


    public List<String> getRoles() {
        List<String> roles = new ArrayList<>();

        if (this.role) {
            roles.add("ROLE_ADMIN");
        } else {
            roles.add("ROLE_USER");
        }
        return roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<String> roles = getRoles();

        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return this.loginId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

}
