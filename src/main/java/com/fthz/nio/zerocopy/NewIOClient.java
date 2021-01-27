package com.fthz.nio.zerocopy;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class NewIOClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 7001));
        String fileName = "mlc02.jpg";

        FileChannel fileChannel = new FileInputStream(fileName).getChannel();
        long startTime = System.currentTimeMillis();

        //linux下一次即可，windows每次最大8M要分段
        long total = fileChannel.transferTo(0, fileChannel.size(), socketChannel); //底层是零拷贝
        System.out.println("总字节：" + total + "，耗时：" + (System.currentTimeMillis() - startTime));
    }
}
