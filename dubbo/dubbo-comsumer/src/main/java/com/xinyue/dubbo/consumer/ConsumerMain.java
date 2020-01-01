package com.xinyue.dubbo.consumer;

import com.xinyue.dubbo.api.service.ArticleService;
import com.xinyue.dubbo.core.model.Article;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsumerMain {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/dubbo-consumer.xml");
        context.start();
        ArticleService articleService = context.getBean(ArticleService.class);
        Article article = articleService.getArticleById(1);
        System.out.println(article);
        System.in.read();
    }
}
