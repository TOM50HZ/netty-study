package com.fthz.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

public class NettyByteBuf02 {
    public static void main(String[] args) {
        ByteBuf byteBuf = Unpooled.copiedBuffer("Hello,world!", Charset.forName("utf-8"));

        if(byteBuf.hasArray()){
            byte[] content = byteBuf.array();
            System.out.println(byteBuf);
            System.out.println(new String(content, Charset.forName("utf-8")));
            int len = byteBuf.readableBytes(); //返回可读字节数
            System.out.println(byteBuf.arrayOffset()); //偏移量
            System.out.println(byteBuf.readerIndex());
            System.out.println(byteBuf.writerIndex());
            System.out.println(byteBuf.capacity());
            System.out.println(byteBuf.getByte(0)); //会返回ASCII
            System.out.println(byteBuf.getCharSequence(0,4, Charset.forName("utf-8"))); //从0开始，读4个

        }
    }
}
