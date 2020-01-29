package com.xinyue.inetty.dubbo.provider;

import com.xinyue.inetty.dubbo.netty.NettyServer;

public class ServerBootstrap {
    public static void main(String[] args) {
        NettyServer.startServer("127.0.0.1", 7000);
    }
}
