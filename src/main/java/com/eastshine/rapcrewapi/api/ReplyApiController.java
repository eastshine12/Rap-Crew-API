package com.eastshine.rapcrewapi.api;

import com.eastshine.rapcrewapi.dto.CreateReplyDto;
import com.eastshine.rapcrewapi.dto.ReplyDto;
import com.eastshine.rapcrewapi.service.ReplyService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
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

    @GetMapping("/{articleId}")
    public Result getReply(@PathVariable Long articleId) {
        System.out.println("ReplyApiController.getReply");
        List<ReplyDto> replyDtoList = replyService.getReply(articleId);
        return new Result(replyDtoList);
    }


    @PutMapping("{replyId}")
    public Result updateReply(@PathVariable Long replyId, @RequestBody ReplyDto replyDto) {
        System.out.println("ReplyApiController.updateReply");

        boolean result = replyService.updateReply(replyId, replyDto);

        return new Result(result? "success":"fail");
    }

    @PutMapping("/del/{replyId}")
    public Result deleteReply(@PathVariable Long replyId) {
        System.out.println("ReplyApiController.deleteReply");

        boolean result = replyService.deleteReply(replyId);

        return new Result(result? "success":"fail");
    }


    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }

}
