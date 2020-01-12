package com.xinyue.inetty.iio;

import java.nio.IntBuffer;

public class NIOMain {
    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(5);
        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i * 2);
        }
        intBuffer.flip();
//        intBuffer.position(1);
//        System.out.println(intBuffer.get());
//        intBuffer.limit(3);
        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }
    }
}
