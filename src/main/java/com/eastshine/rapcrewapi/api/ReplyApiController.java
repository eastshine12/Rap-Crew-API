package com.eastshine.rapcrewapi.api;

import com.eastshine.rapcrewapi.dto.CreateReplyDto;
import com.eastshine.rapcrewapi.service.ReplyService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/reply")
@RequiredArgsConstructor
public class ReplyApiController {

    private final ReplyService replyService;

    @PostMapping("")
    public Result writeReply(@RequestBody @Valid CreateReplyDto replyDto) {

        Map<String, Object> response = new HashMap<>();
        if(replyService.write(replyDto) != null) {
            response.put("result", "SUCCESS");
        } else {
            response.put("result", "FAIL");
            response.put("reason", "등록된 회원이 없거나 게시글이 존재하지 않습니다.");
        }

        return new Result(response);

    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }

}
