package com.eastshine.rapcrewapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String loginId, password, nickname, email;
    private Boolean emailAgree;

}
