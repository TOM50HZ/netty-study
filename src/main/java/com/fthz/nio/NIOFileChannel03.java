package com.fthz.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileChannel03 {
    public static void main(String[] args) throws Exception{

        FileInputStream fileInputStream = new FileInputStream("1.txt");
        FileChannel fileChannel01 = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("2.txt");
        FileChannel fileChannel02 = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        while (true){//读取

            //复位
            //如果没有这个，那么在flip操作后position将等于limit，没有机会读取任何其它数据
            byteBuffer.clear();

            int read = fileChannel01.read(byteBuffer);
            if(read == -1){ //读取结束
                break;
            }
            //写入
            byteBuffer.flip();
            fileChannel02.write(byteBuffer);
        }

        //关闭流
        fileInputStream.close();
        fileOutputStream.close();

    }
}
