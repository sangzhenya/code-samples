package com.xinyue.dubbo.api.service;

public interface CallbackService {
    void addListener(String key, CallbackListener listener);
}
