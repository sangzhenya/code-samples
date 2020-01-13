package com.xinyue.inetty.iio.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class TransferMain {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("1.png");
        FileOutputStream fileOutputStream = new FileOutputStream("2.png");

        FileChannel sourceChannel = fileInputStream.getChannel();
        FileChannel destChannel = fileOutputStream.getChannel();

        destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
        sourceChannel.close();
        destChannel.close();

        fileInputStream.close();
        fileOutputStream.close();
    }
}
