package com.xinyue.inetty.protobuf.codec;

import com.xinyue.inetty.protobuf.multi.MyData;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

public class NettyServerHandler extends SimpleChannelInboundHandler<MyData.MyMessage> {

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(Unpooled.copiedBuffer("Hello, client", CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyData.MyMessage msg) {
        System.out.println("Address::" + ctx.channel().remoteAddress());
        MyData.MyMessage.DataType dataType = msg.getDataType();
        if(dataType == MyData.MyMessage.DataType.StudentType) {
            MyData.Student student = msg.getStudent();
            System.out.println("Student=" + student.getId() + "::" + student.getName());
        } else if(dataType == MyData.MyMessage.DataType.WorkerType) {
            MyData.Worker worker = msg.getWorker();
            System.out.println("Worker=" + worker.getName() + "::" + worker.getAge());
        } else {
            System.out.println("Not Support");
        }
    }
}
