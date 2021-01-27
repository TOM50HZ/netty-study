package com.fthz.nio.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class GroupChatServer {

    private Selector selector;
    private ServerSocketChannel listenChannel;
    private static final int PORT = 6667;

    public GroupChatServer() {
        try{
            selector = Selector.open();
            listenChannel = ServerSocketChannel.open();
            listenChannel.socket().bind(new InetSocketAddress(PORT));
            listenChannel.configureBlocking(false);
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    //监听
    public void listen(){
        System.out.println("监听线程 " + Thread.currentThread().getName());
        try{
           while (true){
               int count = selector.select();
               if(count > 0){
                   Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                   while(iterator.hasNext()){
                       SelectionKey key = iterator.next();

                       if(key.isAcceptable()){
                           SocketChannel sc = listenChannel.accept();
                           sc.configureBlocking(false);
                           sc.register(selector, SelectionKey.OP_READ);
                           System.out.println(sc.getRemoteAddress() + "上线");
                       }

                       if(key.isReadable()){
                           readData(key);
                       }
                       iterator.remove();
                   }
               } else {
                   System.out.println("2s nothing happened");
               }

           }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {

        }
    }

    //读取数据
    private void readData(SelectionKey key) {
        SocketChannel channel = null;
        try {
            channel = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int count = channel.read(buffer);
            if(count > 0){
                String msg = new String(buffer.array());
                System.out.println("From client " + msg);

                //向其它客户端转发消息
                sendInfoToOtherClients(msg, channel);
            }
        } catch (IOException e) {
            try {
                System.out.println(channel.getRemoteAddress() + "已离线");
                key.cancel(); // 取消注册
                channel.close(); // 关闭通道
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    //转发消息
    private void sendInfoToOtherClients(String msg, SocketChannel self) throws IOException{

        System.out.println("转发给客户端的线程 " + Thread.currentThread().getName());
        //遍历客户端列表
        for(SelectionKey key : selector.keys()){
            Channel targetChannel = key.channel();
            if(targetChannel instanceof SocketChannel && targetChannel != self){
                SocketChannel dest = (SocketChannel)targetChannel;
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                dest.write(buffer);
            }
        }
    }


    public static void main(String[] args) {
        GroupChatServer groupChatServer = new GroupChatServer();
        groupChatServer.listen();
    }
}
//可以写一个Handler
//class MyHandler{
//    public void readData(){}
//    public void sendToOthersClients(){}
//}
