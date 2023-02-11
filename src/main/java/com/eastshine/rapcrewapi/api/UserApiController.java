package com.eastshine.rapcrewapi.api;

import com.eastshine.rapcrewapi.dto.CreateUserRequestDto;
import com.eastshine.rapcrewapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/user")
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PostMapping("")
    public Map<String, Object> saveUser(@RequestBody @Valid CreateUserRequestDto request) {
        System.out.println(request.toString());
        Map<String, Object> response = new HashMap<>();
        if(userService.join(request)) {
            response.put("result", "SUCCESS");
        } else {
            response.put("result", "FAIL");
            response.put("reason", "이미 등록된 회원입니다.");
        }

        return response;
    }


}
