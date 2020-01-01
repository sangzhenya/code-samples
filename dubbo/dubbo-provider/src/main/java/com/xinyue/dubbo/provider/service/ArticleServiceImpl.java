package com.xinyue.dubbo.provider.service;

import com.xinyue.dubbo.api.service.ArticleService;
import com.xinyue.dubbo.core.model.Article;
import org.apache.dubbo.config.annotation.Service;

import java.time.LocalDateTime;

@Service(version = "1.0.1")
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
