package com.xinyue.inetty.iio.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOServerMain {
    public static void main(String[] args) throws IOException {
        // 开启连接
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 绑定端口
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));
        // 设置非阻塞
        serverSocketChannel.configureBlocking(false);
        // 获取 Selector
        Selector selector = Selector.open();
        // 注册 OP_ACCEPT 事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        // 注册的 key 的数量
        System.out.println("Register selectionkey count=" + selector.keys().size());


        while (true) {
            // 等待 1s
            if(selector.select(1000) == 0) {
                System.out.println("Wait 1s, no connection");
                continue;
            }

            // 获取 selectedKeys, 关注事件的集合
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            System.out.println("selectionKeys count = " + selectionKeys.size());

            // 迭代器遍历
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                // 如果是 OP_READ 表明有新连接
                if(key.isAcceptable()) {
                    // 获取连接 SocketChannel
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    System.out.println("Client connected, generate a socketChannel " + socketChannel.hashCode());
                    // 设置非阻塞
                    socketChannel.configureBlocking(false);
                    // 注册 OP_READ 事件
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                    System.out.println("Client connected，register selectionkey count=" + selector.keys().size());
                }
                if(key.isReadable()) {
                    // 通过 SelectionKey 获取 Channel
                    SocketChannel channel = (SocketChannel)key.channel();
                    // 获取 Buffer
                    ByteBuffer buffer = (ByteBuffer)key.attachment();
                    // 读取数据
                    channel.read(buffer);
                    System.out.println("From client " + new String(buffer.array()));
                }
                // 移除 key 防止重复操作
                keyIterator.remove();
            }
        }
    }
}
