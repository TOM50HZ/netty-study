package com.fthz.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NIOClient {
    public static void main(String[] args) throws Exception{

        SocketChannel socketChannel = SocketChannel.open(); //获取通道
        socketChannel.configureBlocking(false);//设置非阻塞
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 6666);//服务器端ip与端口

        if(!socketChannel.connect(inetSocketAddress)){
            while(!socketChannel.finishConnect()){
                System.out.println("连接需要时间，客户端不会阻塞，可以做其它工作");
            }
        }

        //连接成功，发送数据
        String str = "Hello 50HZ";
        ByteBuffer byteBuffer = ByteBuffer.wrap(str.getBytes()); //包一个字节数组到buffer
        socketChannel.write(byteBuffer);
        System.in.read();

    }
}
