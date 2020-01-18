package com.xinyue.inetty.inetty.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.time.LocalDateTime;

public class MyWebsocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) {
        System.out.println("Receive Msg::" + msg.text());
        ctx.channel().writeAndFlush(new TextWebSocketFrame("Server Time:" + LocalDateTime.now() + "    " + msg.text()));
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        System.out.println("Add::" + ctx.channel().id().asLongText());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        System.out.println("Removed::" + ctx.channel().id().asLongText());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }
}
