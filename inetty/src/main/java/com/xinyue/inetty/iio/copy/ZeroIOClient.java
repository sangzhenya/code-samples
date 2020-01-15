package com.xinyue.inetty.iio.copy;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class ZeroIOClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 8080));
        String filename = "demo.zip";
        FileChannel fileChannel = new FileInputStream(filename).getChannel();
        long startTime = System.currentTimeMillis();
        long transferCount = fileChannel.transferTo(0, fileChannel.size(), socketChannel);
        System.out.println("Total count =" + transferCount + " timeout:" + (System.currentTimeMillis() - startTime));
        fileChannel.close();
    }
}
