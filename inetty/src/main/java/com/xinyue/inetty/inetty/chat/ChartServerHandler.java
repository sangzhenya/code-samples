package com.xinyue.inetty.inetty.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChartServerHandler extends SimpleChannelInboundHandler<String> {
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) {
        Channel channel = ctx.channel();
        channelGroup.forEach(ch -> {
            if (channel != ch) {
                ch.writeAndFlush(sdf.format(new Date()) + "\t" + "[Client:" + channel.remoteAddress() + "] say: " + msg + "\n");
            } else {
                ch.writeAndFlush(sdf.format(new Date()) + "\t" + msg + "\n");
            }
        });
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush(sdf.format(new Date()) + "\t" + "[Client:" + channel.remoteAddress() + "] join in \n");
        channelGroup.add(channel);
        System.out.println(channelGroup.size());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush(sdf.format(new Date()) + "\t" + "[Client:" + channel.remoteAddress() + "] leave \n");
        System.out.println(channelGroup.size());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println(ctx.channel().remoteAddress() + " active ...");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println(ctx.channel().remoteAddress() + " inactive ...");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }
}
