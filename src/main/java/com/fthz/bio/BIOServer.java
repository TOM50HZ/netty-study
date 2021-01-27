package com.fthz.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIOServer {
    public static void main(String[] args) throws Exception {
        //创建线程池
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        System.out.println("启动");
        //创建ServerSocket
        ServerSocket serverSocket = new ServerSocket(6666);
        while (true){
            //监听,等待客户端连接
            final Socket socket = serverSocket.accept();
            System.out.println("连接客户端");
            //创建线程 与之通讯
            newCachedThreadPool.execute(new Runnable() {

                public void run() {
                    //可以和客户端通讯
                    handler(socket);
                }
            });
        }
    }

    //编写一个handler
    public static void handler(Socket socket){
        byte[] bytes = new byte[1024];

        try {
            //获取输入流
            InputStream inputStream = socket.getInputStream();

            //读取客户端信息
            while(true){
                int read = inputStream.read(bytes);
                if(read != -1){
                    System.out.println(bytes.toString());
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("关闭连接");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
