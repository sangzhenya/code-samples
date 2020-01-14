package com.xinyue.inetty.iio.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class CopyFileMain {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("1.txt");
        FileChannel readChannel = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("2.txt");
        FileChannel writeChannel = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        while (true) {
            byteBuffer.clear();
            int read = readChannel.read(byteBuffer);
            System.out.println("read =" + read);
            if(read == -1) {
                break;
            }
            byteBuffer.flip();
            writeChannel.write(byteBuffer);
        }

        fileInputStream.close();
        fileOutputStream.close();
    }
}
