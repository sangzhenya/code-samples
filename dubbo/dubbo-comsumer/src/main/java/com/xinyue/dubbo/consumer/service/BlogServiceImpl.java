package com.xinyue.dubbo.consumer.service;

import com.xinyue.dubbo.api.service.ArticleService;
import com.xinyue.dubbo.api.service.BlogService;
import com.xinyue.dubbo.core.model.Article;

public class BlogServiceImpl implements BlogService {
    ArticleService articleService;

    @Override
    public void getService() {
        Article article = articleService.getArticleById(1);
        System.out.println(article);
    }
}
