package com.xinyue.inetty.iio.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class ChatServer {
    private Selector selector;
    private ServerSocketChannel listenChannel;
    private static final int PORT = 8080;

    public ChatServer() {
        try {
            selector = Selector.open();
            listenChannel = ServerSocketChannel.open();
            listenChannel.socket().bind(new InetSocketAddress(PORT));
            listenChannel.configureBlocking(false);
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void listen() {
        System.out.println("Listen: " + Thread.currentThread().getName());
        try {
            while (true) {
                int count = selector.select();
                if (count > 0) {
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        if (key.isAcceptable()) {
                            SocketChannel sc = listenChannel.accept();
                            sc.configureBlocking(false);
                            sc.register(selector, SelectionKey.OP_READ);
                            System.out.println(sc.getRemoteAddress() + " Connected ");

                        }
                        if (key.isReadable()) {
                            readData(key);
                        }
                        iterator.remove();
                    }

                } else {
                    System.out.println("Wait....");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
        }
    }

    private void readData(SelectionKey key) {
        SocketChannel channel = null;

        try {
            channel = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            int count = channel.read(buffer);
            if (count > 0) {
                String msg = new String(buffer.array());
                System.out.println("form client: " + msg);
                sendInfoToOtherClients(msg, channel);
            }

        } catch (IOException e) {
            try {
                System.out.println(channel.getRemoteAddress() + " Disconnect..");
                key.cancel();
                channel.close();
            } catch (IOException e2) {
                e2.printStackTrace();
                ;
            }
        }
    }

    private void sendInfoToOtherClients(String msg, SocketChannel self) throws IOException {
        System.out.println("Server dispatch message thread: " + Thread.currentThread().getName());
        for (SelectionKey key : selector.keys()) {
            Channel targetChannel = key.channel();
            if (targetChannel instanceof SocketChannel && targetChannel != self) {
                SocketChannel dest = (SocketChannel) targetChannel;
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                dest.write(buffer);
            }
        }

    }

    public static void main(String[] args) {
        ChatServer groupChatServer = new ChatServer();
        groupChatServer.listen();
    }
}
