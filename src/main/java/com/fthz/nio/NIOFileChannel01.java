package com.fthz.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileChannel01 {
    public static void main(String[] args) throws  Exception{
        String str = "hello world!";
        //创建一个输出流->channel
        FileOutputStream fileOutputStream = new FileOutputStream("F:\\FTHZ\\file01.txt");

        //通过输出流获取对应的 FileChannel
        FileChannel fileChannel = fileOutputStream.getChannel();

        //创建一个缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //将String写入byteBuffer
        byteBuffer.put(str.getBytes());

        //读写切换
        byteBuffer.flip();

        //写入channel
        fileChannel.write(byteBuffer);
        fileOutputStream.close();

    }
}
