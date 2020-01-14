package com.xinyue.inetty.iio.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MapperChannelMain {
    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("01.txt", "rw");
        FileChannel channel = randomAccessFile.getChannel();

        // map 的三个参数分别是：读写模式；直接修改的起始位置；映射到内存大小。
        // 本质上还是 DirectByteBuffer
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);

        mappedByteBuffer.put(0, (byte) '0');
        mappedByteBuffer.put(3, (byte) '9');
        // IndexOutOfBoundsException
//        mappedByteBuffer.put(5, (byte) 'Y');

        while (mappedByteBuffer.hasRemaining()) {
            System.out.println((char)mappedByteBuffer.get());
        }
        randomAccessFile.close();
    }
}
