package com.xinyue.inetty.protobuf.codec;

import com.xinyue.inetty.protobuf.multi.MyData;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.Random;

public class NettyClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        int random = new Random().nextInt(3);
        MyData.MyMessage myMessage;
        if(0 == random) {
            myMessage = MyData.MyMessage.newBuilder().setDataType(MyData.MyMessage.DataType.StudentType).setStudent(MyData.Student.newBuilder().setId(1).setName("Xinyue").build()).build();
        } else {
            myMessage = MyData.MyMessage.newBuilder().setDataType(MyData.MyMessage.DataType.WorkerType).setWorker(MyData.Worker.newBuilder().setAge(2).setName("Xiahua").build()).build();
        }
        ctx.writeAndFlush(myMessage);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf buffer = (ByteBuf) msg;
        System.out.println("Receive:" + buffer.toString(CharsetUtil.UTF_8));
        System.out.println("Address： "+ ctx.channel().remoteAddress());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }
}
