package com.eastshine.rapcrewapi.dto;

import com.eastshine.rapcrewapi.domain.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateUserRequestDto {

    private String password, nickname, email;
    private Boolean emailAgree, role, enabled;

    @Builder
    public UpdateUserRequestDto(String password, String nickname, String email,
                                Boolean emailAgree, Boolean role, Boolean enabled) {
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.emailAgree = emailAgree;
        this.role = role;
        this.enabled = enabled;
    }
}
