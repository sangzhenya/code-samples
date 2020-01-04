package com.xinyue.dubbo.api.service;

import java.util.concurrent.CompletableFuture;

public interface AsyncService {
    CompletableFuture<String> sayHello(String name);
}
