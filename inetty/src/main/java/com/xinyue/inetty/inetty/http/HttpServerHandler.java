package com.xinyue.inetty.inetty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;
import java.net.URISyntaxException;

// 客户端和服务器端相互通讯的数据被封装成 HttpObject
public class HttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws URISyntaxException {
        System.out.println("Channel::" + ctx.channel());
        System.out.println("Pipeline::" + ctx.pipeline());
        System.out.println("Pipeline Channel::" + ctx.pipeline().channel());

        System.out.println("Handler" + ctx.handler());

        if (msg instanceof HttpRequest) {
            System.out.println("Ctx class" + ctx.getClass());
            System.out.println("Pipeline hashCode" + ctx.pipeline().hashCode());
            System.out.println("This hashCode" + this.hashCode());
            System.out.println("Msg" + msg.getClass());
            System.out.println("Address" + ctx.channel().remoteAddress());

            HttpRequest httpRequest = (HttpRequest) msg;
            URI uri = new URI(httpRequest.uri());
            if("/favicon.ico".equals(uri.getPath())) {
                System.out.println("Ico, just return");
                return;
            }

            System.out.println("====================================================================");
            System.out.println("********************************************************************");
            System.out.println("====================================================================");

            ByteBuf content = Unpooled.copiedBuffer("Hello, Client", CharsetUtil.UTF_8);
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
            ctx.writeAndFlush(response);
        } else {
            System.out.println("=====msg=====");
            System.out.println(msg);
            System.out.println("=====msg=====");
        }
    }
}
