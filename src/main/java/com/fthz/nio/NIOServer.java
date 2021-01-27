package com.fthz.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {
    public static void main(String[] args) throws Exception {

        //创建ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        //得到一个Selector
        Selector selector = Selector.open();

        //绑定一个端口，在服务器端监听
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));

        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);

        //serverSocketChannel 注册到selector，关心OP_ACCEPT
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        //循环等待客户端连接
        while(true){
            if(selector.select(1000) == 0){ //没有事件发生
                System.out.println("1s nothing happened");
                continue;
            }

            //有关注事件，获取到selectionKeys集合
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            //遍历
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while(keyIterator.hasNext()){
                SelectionKey key = keyIterator.next();
                if(key.isAcceptable()){
                    SocketChannel socketChannel = serverSocketChannel.accept(); //连接
                    System.out.println("客户端生成连接");
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));//注册到Selector并关联一个Buffer
                }
                if(key.isReadable()){
                    SocketChannel channel = (SocketChannel)key.channel();//获取Channel
                    ByteBuffer buffer = (ByteBuffer)key.attachment();//获取Buffer
                    channel.read(buffer);
                    System.out.println("From Client " + new String(buffer.array()));
                }

                keyIterator.remove();//主动移除key，防止重复操作
            }


        }


    }
}
