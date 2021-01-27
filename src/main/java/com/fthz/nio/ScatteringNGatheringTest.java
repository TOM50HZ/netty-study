package com.fthz.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * Scattering: 数据写入Buffer时，可以采用Buffer数组依次写入 (分散
 * Gathering: 从Buffer读取数据时，可以采用Buffer数组依次读出 (聚合
 */
public class ScatteringNGatheringTest {
    public static void main(String[] args) throws Exception{

        // 使用ServerSocketChannel 和 SocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(7000);

        //绑定端口到socket并启动
        serverSocketChannel.socket().bind(inetSocketAddress);

        //创建一个Buffer数组
        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);

        //等客户端连接
        SocketChannel socketChannel = serverSocketChannel.accept();
        int msgLength = 8; //从客户端接收8个字节
        //循环读取
        while (true) {

            int byteRead = 0;

            while(byteRead < msgLength){
                long l = socketChannel.read(byteBuffers);
                byteRead += l;
                System.out.println("now read = " + byteRead);
                //使用流打印
                Arrays.asList(byteBuffers).stream().map(buffer ->
                        "position=" + buffer.position() + " limit=" + buffer.limit()
                ).forEach(System.out::println);
            }
            //将所有的Buffer进行flip
            Arrays.asList(byteBuffers).forEach(buffer -> buffer.flip());

            //将数据读出显示到客户端
            long byteWrite = 0;
            while (byteWrite < msgLength){
                Long l = socketChannel.write(byteBuffers);
                byteWrite += l;
            }

            //将所有的Buffer进行clear
            Arrays.asList(byteBuffers).forEach(buffer -> {
                buffer.clear();
            });
            System.out.println("Read = " + byteRead + " Write = " + byteWrite + " msgLength" + msgLength);
        }
    }
}
