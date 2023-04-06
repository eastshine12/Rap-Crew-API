package com.eastshine.rapcrewapi.api;

import com.eastshine.rapcrewapi.dto.CreateReplyDto;
import com.eastshine.rapcrewapi.dto.LikeyDto;
import com.eastshine.rapcrewapi.dto.ReplyDto;
import com.eastshine.rapcrewapi.service.LikeyService;
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
@RequestMapping(path = "/api/likey")
@RequiredArgsConstructor
public class LikeyApiController {

    private final LikeyService likeyService;


    @PostMapping("/liked")
    public Result isLiked(@RequestBody LikeyDto likeyDto) {
        System.out.println("LikeyApiController.isLiked");
        Long result = likeyService.isLiked(likeyDto);
        return new Result(result>0?"success":"fail");
    }

    @PostMapping("/unliked")
    public Result isUnliked(@RequestBody LikeyDto likeyDto) {
        System.out.println("LikeyApiController.isUnliked");
        Long result = likeyService.isUnliked(likeyDto);
        return new Result(result>0?"success":"fail");
    }


    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }

}
