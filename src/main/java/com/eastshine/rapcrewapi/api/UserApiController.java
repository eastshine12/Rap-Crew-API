package com.eastshine.rapcrewapi.api;

import com.eastshine.rapcrewapi.domain.User;
import com.eastshine.rapcrewapi.dto.CreateUserRequestDto;
import com.eastshine.rapcrewapi.dto.UpdateUserRequestDto;
import com.eastshine.rapcrewapi.dto.UserDto;
import com.eastshine.rapcrewapi.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/user")
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;



    @GetMapping("")
    public UserDto user(@RequestBody UserDto userDto) {
        System.out.println(userDto.toString());
        User user = userService.findUser(userDto.getLoginId());
        userDto.setEmail(user.getEmail());
        userDto.setNickname(user.getNickname());
        userDto.setEmailAgree(user.getEmailAgree());
        return userDto;

    }
    @GetMapping("/all")
    public Result users() {

        List<User> findUsers = userService.findUsers();
        List<UserDto> collect = findUsers.stream()
                .map(m -> new UserDto(m.getLoginId(), m.getNickname(), m.getEmail(), m.getEmailAgree()))
                .collect(Collectors.toList());

        return new Result(collect);

    }

    @PostMapping("")
    public Map<String, Object> saveUser(@RequestBody @Valid CreateUserRequestDto request) {

        /* response dto 생성하기 */
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

        boolean result = userService.update(id, request);

        return new UpdateUserResponse(result?"SUCCESS":"FAIL", result?"":"user does not exist.", id);

    }


    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class UpdateUserResponse {
        private String result;
        private String reason;
        private Long id;
    }

}
