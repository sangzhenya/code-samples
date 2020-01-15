package com.xinyue.inetty.iio.copy;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class TraIOClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8080);

        String fileName = "demo.zip";
        InputStream inputStream = new FileInputStream(fileName);

        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

        byte[] buffer = new byte[4096];
        long readCount;
        long total = 0;

        long startTime = System.currentTimeMillis();

        while ((readCount = inputStream.read(buffer)) >= 0) {
            total += readCount;
            dataOutputStream.write(buffer);
        }

        System.out.println("Total count： " + total + ", timeout： " + (System.currentTimeMillis() - startTime));
        dataOutputStream.close();
        socket.close();
        inputStream.close();
    }
}
