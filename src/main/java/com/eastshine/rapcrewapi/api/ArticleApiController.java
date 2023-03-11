package com.eastshine.rapcrewapi.api;

import com.eastshine.rapcrewapi.domain.Article;
import com.eastshine.rapcrewapi.dto.ArticleDto;
import com.eastshine.rapcrewapi.dto.CreateArticleDto;
import com.eastshine.rapcrewapi.dto.UserDto;
import com.eastshine.rapcrewapi.service.ArticleService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/article")
@RequiredArgsConstructor
public class ArticleApiController {

    private final ArticleService articleService;


    @PostMapping("")
    public Map<String, Object> writeArticle(@RequestBody @Valid CreateArticleDto articleDto) {

        Map<String, Object> response = new HashMap<>();
        if(articleService.write(articleDto) != null) {
            response.put("result", "SUCCESS");
        } else {
            response.put("result", "FAIL");
            response.put("reason", "이미 등록된 회원입니다.");
        }

        return response;

    }

    @GetMapping("/all")
    public Result getArticleAll() {
        System.out.println("ArticleApiController.getArticleAll");
        List<ArticleDto> all = articleService.getArticleAll();
        return new Result(all);
    }

    @GetMapping("")
    public Result getArticleByUserId(@RequestBody UserDto userDto) {
        System.out.println("ArticleApiController.getArticleById");
        System.out.println("userDto = " + userDto);

        return new Result(null);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }


}
