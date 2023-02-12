package com.eastshine.rapcrewapi.api;

import com.eastshine.rapcrewapi.dto.CreateUserRequestDto;
import com.eastshine.rapcrewapi.dto.UpdateUserRequestDto;
import com.eastshine.rapcrewapi.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

        Map<String, Object> response = new HashMap<>();
        if(userService.join(request)) {
            response.put("result", "SUCCESS");
        } else {
            response.put("result", "FAIL");
            response.put("reason", "이미 등록된 회원입니다.");
        }

        return response;
    }

    @PutMapping("/{id}")
    public UpdateUserResponse updateUser(@PathVariable("id") Long id,
                                         @RequestBody @Valid UpdateUserRequestDto request) {

        System.out.println(request.toString());
        System.out.println("id = " + id);

        userService.update(id, request);


        return new UpdateUserResponse("SUCCESS", "", id);

    }

    @Data
    @AllArgsConstructor
    static class UpdateUserResponse {
        private String result;
        private String reason;
        private Long id;
    }

}
