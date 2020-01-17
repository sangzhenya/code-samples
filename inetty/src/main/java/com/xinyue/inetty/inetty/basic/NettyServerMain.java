package com.xinyue.inetty.inetty.basic;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServerMain {
    public static void main(String[] args) throws InterruptedException {
        // 创建两个线程组, 两个都是无限循环
        // bossGroup 负责处理连接请求，WorkGroup 负责执行具体业务逻辑
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        // 默认数量为 CPU 核心数的 2 倍
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            // 创建服务端的启动对象并配置参数
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workGroup) // 设置两个线程组
                    .channel(NioServerSocketChannel.class) // 使用 NioServerSocketChannel 作为服务器 Channel 的实现
                    .option(ChannelOption.SO_BACKLOG, 128) // 设置线程队列得到连接个数
                    .childOption(ChannelOption.SO_KEEPALIVE, true) //设置保持活动连接状态
                    .childHandler(new ChannelInitializer<>() { //创建一个 Channel 初始化对象(匿名对象)
                        @Override
                        protected void initChannel(Channel channel) {
                            System.out.println(channel.hashCode());
                            channel.pipeline().addLast(new NettyServerHandler());
                        }
                    });
            System.out.println("System is ready");

            // 启动服务并绑定端口
            ChannelFuture channelFuture = serverBootstrap.bind(8080).sync();
            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) {
                    if (future.isSuccess()) {
                        System.out.println("success");
                    } else {
                        System.out.println("fail");
                    }

                }
            });

            // 关闭对 Channel 的监听
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
