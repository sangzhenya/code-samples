package com.xinyue.inetty.inetty.basic;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.util.CharsetUtil;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     *
     * @param ctx  上下文 包含 pipeline 和 Channel
     * @param msg  客户端发送的 msg
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println(Thread.currentThread().getName() + "::" + ctx.channel() + "::" + ctx);
        Channel channel = ctx.channel();
        ChannelPipeline pipeline = ctx.pipeline();
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("Receive::" + buf.toString(CharsetUtil.UTF_8));
        System.out.println("Address::" + channel.remoteAddress());
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(Unpooled.copiedBuffer("Hello, client", CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }
}
