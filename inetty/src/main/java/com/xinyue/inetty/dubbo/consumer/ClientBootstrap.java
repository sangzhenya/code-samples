package com.xinyue.inetty.dubbo.consumer;

import com.xinyue.inetty.dubbo.api.HelloService;
import com.xinyue.inetty.dubbo.netty.NettyClient;

public class ClientBootstrap {
    public static final String PROVIDER_NAME = "HelloService#hello#";

    public static void main(String[] args) throws InterruptedException {
        NettyClient customer = new NettyClient();
        HelloService service = (HelloService) customer.getBean(HelloService.class, PROVIDER_NAME);
        for (;; ) {
            Thread.sleep(5 * 1000);
            String res = service.hello("Hello World");
            System.out.println("Result::" + res);
        }
    }
}
