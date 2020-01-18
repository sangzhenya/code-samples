package com.xinyue.inetty.inetty.unpooled;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class MyUnPooled {
    public static void main(String[] args) {
        ByteBuf buffer = Unpooled.buffer(10);

        for(int i = 0; i < 10; i++) {
            buffer.writeByte(i);
        }

        System.out.println("capacity=" + buffer.capacity());//10
        for(int i = 0; i < buffer.capacity(); i++) {
            System.out.println(buffer.readByte());
        }
        System.out.println("执行完毕");
    }
}
