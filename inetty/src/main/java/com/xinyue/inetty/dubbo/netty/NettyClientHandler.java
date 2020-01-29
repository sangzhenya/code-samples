package com.xinyue.inetty.dubbo.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;

public class NettyClientHandler extends ChannelInboundHandlerAdapter implements Callable {
    private ChannelHandlerContext context;
    private String result;
    private String para;

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("Channel Active");
        context = ctx;
    }

    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("Channel Read");
        result = msg.toString();
        notify();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }

    @Override
    public synchronized Object call() throws Exception {
        System.out.println("Call 1");
        context.writeAndFlush(para);
        wait();
        System.out.println("Call 2");
        return result;
    }

    void setPara(String para) {
        System.out.println("SetPara");
        this.para = para;
    }
}
