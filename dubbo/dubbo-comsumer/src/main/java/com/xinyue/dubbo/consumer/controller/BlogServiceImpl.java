package com.xinyue.dubbo.consumer.controller;

import com.xinyue.dubbo.api.service.ArticleService;
import com.xinyue.dubbo.api.service.AsyncService;
import com.xinyue.dubbo.api.service.CallbackListener;
import com.xinyue.dubbo.api.service.CallbackService;
import com.xinyue.dubbo.core.model.Article;
import org.apache.dubbo.config.annotation.Argument;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class BlogServiceImpl{
    @Reference(stub = "com.xinyue.dubbo.consumer.controller.ArticleServiceStub")
    ArticleService articleService;
    @Reference(methods = {@Method(name = "addListener", arguments = {@Argument(index = 1, callback = true)})})
    CallbackService callbackService;
    @Reference(timeout = 1000)
    AsyncService asyncService;

    @GetMapping("/")
    public Article hello() {
        return articleService.getArticleById(1);
    }

    @GetMapping("/callback")
    public String callback() {
        callbackService.addListener("foo.bar", msg -> System.out.println("callback1:" + msg));
        return "success";
    }

    @GetMapping("async")
    public String async() {
        // 调用直接返回CompletableFuture
        CompletableFuture<String> future = asyncService.sayHello("async call request");
        // 增加回调
        future.whenComplete((v, t) -> {
            if (t != null) {
                t.printStackTrace();
            } else {
                System.out.println("Response: " + v);
            }
        });
        // 早于结果输出
        System.out.println("Executed before response return.");
        return "Success";
    }
}

