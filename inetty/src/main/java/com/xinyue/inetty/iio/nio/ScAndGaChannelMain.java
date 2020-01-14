package com.xinyue.inetty.iio.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

public class ScAndGaChannelMain {
    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(8080);

        serverSocketChannel.socket().bind(inetSocketAddress);

        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);

        SocketChannel socketChannel = serverSocketChannel.accept();
        int messageLength = 8;
        while (true) {
            int byteRead = 0;

            while (byteRead < messageLength) {
                long l = socketChannel.read(byteBuffers);
                byteRead += l;
                System.out.println("byteRead=" + byteRead);
                Arrays.stream(byteBuffers).map(buffer -> "position=" + buffer.position() + ", limit=" + buffer.limit()).forEach(System.out::println);
            }

            Arrays.asList(byteBuffers).forEach(ByteBuffer::flip);

            long byteWirte = 0;
            while (byteWirte < messageLength) {
                long l = socketChannel.write(byteBuffers);
                byteWirte += l;
            }

            Arrays.asList(byteBuffers).forEach(ByteBuffer::clear);

            System.out.println("byte read=" + byteRead + " byte write=" + byteWirte + ", message length" + messageLength);
        }
    }
}
