package com.eastshine.rapcrewapi.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {

    private String loginId, nickname, email;
    private Boolean emailAgree;

}
