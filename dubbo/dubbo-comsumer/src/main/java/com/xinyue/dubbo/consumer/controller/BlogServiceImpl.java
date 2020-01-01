package com.xinyue.dubbo.consumer.controller;

import com.xinyue.dubbo.api.service.ArticleService;
import com.xinyue.dubbo.core.model.Article;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogServiceImpl{
    @Reference(version = "1.0.1")
    ArticleService articleService;

    @GetMapping("/")
    public Article hello() {
        return articleService.getArticleById(1);
    }
}
