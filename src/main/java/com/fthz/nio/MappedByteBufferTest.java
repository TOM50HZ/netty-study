package com.fthz.nio;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 说明
 * 1. MappedByteBuffer 可让文件直接在内存（堆外内存）中修改，i.e.操作系统不需要拷贝一次
 */

public class MappedByteBufferTest {
    public static void main(String[] args) throws Exception {
        RandomAccessFile randomAccessFile = new RandomAccessFile("1.txt", "rw");;
        FileChannel channel = randomAccessFile.getChannel();
        /**
         * 读写模式
         * 可直接修改的起始位置
         * 映射到内存的大小，即将1.txt的多少个字节映射到内存
         * i.e. 可直接修改范围为第0~4字节，注意是字节
         */
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        mappedByteBuffer.put(0,(byte)'H');
        mappedByteBuffer.put(3,(byte)'9');
        randomAccessFile.close();

    }
}
