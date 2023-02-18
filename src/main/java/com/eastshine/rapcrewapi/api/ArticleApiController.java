package com.eastshine.rapcrewapi.api;

import com.eastshine.rapcrewapi.dto.CreateArticleDto;
import com.eastshine.rapcrewapi.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
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



}
