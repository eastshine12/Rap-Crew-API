package com.eastshine.rapcrewapi.dto;

import com.eastshine.rapcrewapi.domain.User;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateUserRequestDto {

    @NotNull
    private String loginId, password, nickname, email;
    @NotNull
    private boolean emailAgree, role, enabled;

    @Builder
    public CreateUserRequestDto(String loginId, String password, String nickname, String email,
                                boolean emailAgree, boolean role, boolean enabled) {
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.emailAgree = emailAgree;
        this.role = role;
        this.enabled = enabled;
    }

    public User toEntity() {
        return User.builder()
                .loginId(loginId)
                .password(password)
                .nickname(nickname)
                .email(email)
                .emailAgree(emailAgree)
                .role(role)
                .enabled(enabled)
                .build();
    }

}
