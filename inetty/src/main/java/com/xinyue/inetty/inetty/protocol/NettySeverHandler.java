package com.xinyue.inetty.inetty.protocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class NettySeverHandler extends SimpleChannelInboundHandler<MessageProtocol> {
    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws UnsupportedEncodingException {
        int len = msg.getLen();
        byte[] content = msg.getContent();

        System.out.println("========");
        System.out.println("Length::" + len);
        System.out.println("Content::" + new String(content, StandardCharsets.UTF_8));

        System.out.println("Message Count=" + (++this.count));

        String responseContent = UUID.randomUUID().toString();
        int responseLen = responseContent.getBytes(StandardCharsets.UTF_8).length;
        byte[] responseContent2 = responseContent.getBytes(StandardCharsets.UTF_8);

        MessageProtocol messageProtocol = new MessageProtocol();
        messageProtocol.setLen(responseLen);
        messageProtocol.setContent(responseContent2);

        ctx.writeAndFlush(messageProtocol);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }
}
