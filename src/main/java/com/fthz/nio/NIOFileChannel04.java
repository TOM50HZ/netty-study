package com.fthz.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class NIOFileChannel04 {
    public static void main(String[] args) throws Exception{
        //创建相关流
        FileInputStream fileInputStream = new FileInputStream("F:\\FTHZ\\mlc01.jpg");
        FileOutputStream fileOutputStream = new FileOutputStream("F:\\FTHZ\\mlc02.jpg");
        FileChannel srcCh = fileInputStream.getChannel();
        FileChannel destCh = fileOutputStream.getChannel();

        //直接完成拷贝
        destCh.transferFrom(srcCh, 0, srcCh.size());

        //关闭相关流
        srcCh.close();
        destCh.close();
        fileInputStream.close();
        fileOutputStream.close();
    }
}
