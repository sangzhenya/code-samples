package com.xinyue.inetty.iio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIOMain {
    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("Server start");
        while (true) {
            System.out.println(Thread.currentThread().getId() + "::" + Thread.currentThread().getName());
            final Socket socket = serverSocket.accept();
            System.out.println("Wait connect");
            System.out.println("Connected");
            executorService.execute(() -> handler(socket));
        }

    }

    private static void handler(Socket socket) {
        try {
            System.out.println(Thread.currentThread().getId() + "::" + Thread.currentThread().getName());
            byte[] bytes = new byte[1024];
            InputStream inputStream = socket.getInputStream();
            while (true) {
                System.out.println(Thread.currentThread().getId() + "::" + Thread.currentThread().getName());
                System.out.println("Read....");
                int read = inputStream.read(bytes);
                if (read != -1) {
                    System.out.println(new String(bytes, 0, read));
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Close client connect");
            try {
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
