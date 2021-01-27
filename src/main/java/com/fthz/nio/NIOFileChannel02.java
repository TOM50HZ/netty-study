package com.fthz.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileChannel02 {
    public static void main(String[] args) throws Exception {
        //创建输入流
        File file = new File("F:\\FTHZ\\file01.txt");
        FileInputStream fileInputStream = new FileInputStream(file);

        //获取channel
        FileChannel fileChannel = fileInputStream.getChannel();

        //缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate((int)file.length());

        //将通道数据读入到buffer中
        fileChannel.read(byteBuffer);

        //将字节转成字符串
        System.out.println(new String(byteBuffer.array()));
        fileInputStream.close();
    }
}
