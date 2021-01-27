package com.fthz.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class NettyByteBuf01 {
    public static void main(String[] args) {

        ByteBuf buffer = Unpooled.buffer(10);
        for (int i = 0; i < 10; i++){
            buffer.writeByte(i);
        }

        //不需要flip()，底层维护了readerIndex/writerIndex
        /*
        * 分成三个区域
        * 0 ~ readerIndex: 已经读取区域
        * readerIndex ~ writerIndex: 可读区域
        * writerIndex~capacity: 可写区域
        * */
        for (int i = 0; i < buffer.capacity(); i++){
            //System.out.println(buffer.getByte(i));
            System.out.println(buffer.readByte());
        }
    }
}
