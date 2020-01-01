package com.xinyue.dubbo.provider.service;

import com.xinyue.dubbo.api.service.ArticleService;
import com.xinyue.dubbo.core.model.Article;

import java.time.LocalDateTime;

public class ArticleServiceImpl implements ArticleService {
    @Override
    public Article getArticleById(Integer id) {
        Article article = new Article();
        article.setId(id);
        article.setTitle("Title: " + id);
        article.setSummary("Summary: " + id);
        article.setCreateDate(LocalDateTime.now());
        article.setLastUpdateDate(LocalDateTime.now());
        return article;
    }
}
