package com.xinyue.dubbo.consumer.controller;

import com.xinyue.dubbo.api.service.ArticleService;
import com.xinyue.dubbo.core.model.Article;

public class ArticleServiceStub implements ArticleService {

    private final ArticleService articleService;

    public ArticleServiceStub(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Override
    public Article getArticleById(Integer id) {
        // 此代码在客户端执行, 你可以在客户端做ThreadLocal本地缓存，或预先验证参数是否合法，等等
        try {
            return articleService.getArticleById(id);
        } catch (Exception e) {
            // 你可以容错，可以做任何AOP拦截事项
            return new Article();
        }
    }
}
