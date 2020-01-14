package com.xinyue.inetty.iio.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NIOClientMain {
    public static void main(String[] args) throws IOException {
        // 开启连接
        SocketChannel socketChannel = SocketChannel.open();
        // 设置非阻塞
        socketChannel.configureBlocking(false);
        // 连接端口
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 8080);
        // 连接
        if (!socketChannel.connect(inetSocketAddress)) {
            // 等待连接完成
            while (!socketChannel.finishConnect()) {
                System.out.println("No blocking...");
            }
        }

        String str = "Hello, 新月~";
        // 包装数据
        ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());
        // 写数据
        socketChannel.write(buffer);
        System.in.read();
    }
}
