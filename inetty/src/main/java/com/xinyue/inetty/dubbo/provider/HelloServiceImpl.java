package com.xinyue.inetty.dubbo.provider;

import com.xinyue.inetty.dubbo.api.HelloService;

public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String msg) {
        System.out.println("Receive Msg::" + msg);
        return msg;
    }

}
