package com.xinyue.inetty.inetty.inout;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class NettyServerHandler extends SimpleChannelInboundHandler<Long> {

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(1000L);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Long msg) {
        System.out.println("Address::" + ctx.channel().remoteAddress());
        System.out.println("Msg::" + msg);
    }
}
