package com.eastshine.rapcrewapi.api;

import com.eastshine.rapcrewapi.domain.Article;
import com.eastshine.rapcrewapi.dto.ArticleDto;
import com.eastshine.rapcrewapi.dto.CreateArticleDto;
import com.eastshine.rapcrewapi.dto.UpdateArticleRequestDto;
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
    public Result writeArticle(@RequestBody @Valid CreateArticleDto articleDto) {

        Map<String, Object> response = new HashMap<>();
        if(articleService.write(articleDto) != null) {
            response.put("result", "SUCCESS");
        } else {
            response.put("result", "FAIL");
            response.put("reason", "등록된 회원이 없습니다.");
        }

        return new Result(response);

    }

    @GetMapping("/all")
    public Result getArticleAll() {
        System.out.println("ArticleApiController.getArticleAll");
        List<ArticleDto> all = articleService.getArticleAll();
        return new Result(all);
    }


    @GetMapping("/{articleId}")
    public Result getArticle(@PathVariable Long articleId) {
        System.out.println("ArticleApiController.getArticle");

        return new Result(articleService.getArticle(articleId));
    }

    @PutMapping("/{articleId}")
    public Result updateArticle(@PathVariable Long articleId,
                                @RequestBody UpdateArticleRequestDto articleRequestDto) {
        System.out.println("ArticleApiController.updateArticle");
        System.out.println("articleId = " + articleId);
        System.out.println("articleRequestDto = " + articleRequestDto);

        boolean result = articleService.updateArticle(articleId, articleRequestDto);

        return new Result(result? "success":"fail");
    }

    @PutMapping("/del/{articleId}")
    public Result deleteArticle(@PathVariable Long articleId) {
        System.out.println("ArticleApiController.deleteArticle");
        System.out.println("articleId = " + articleId);

        boolean result = articleService.deleteArticle(articleId);

        return new Result(result? "success":"fail");
    }


    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }


}
