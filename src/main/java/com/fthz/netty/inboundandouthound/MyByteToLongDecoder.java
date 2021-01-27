package com.fthz.netty.inboundandouthound;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class MyByteToLongDecoder extends ByteToMessageDecoder {

    /*
    *   decode会根据接收的数据被调用多次，直到没有新元素被加到List
    *   或者ByteBuf没有更多的可读字节为止
    *   如果List out不为空，则将其传给下一个ChannelInboundHandler，类似滑动窗口
    * */

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("MyByteToLongDecoder decode被调用");
        //Long 8个字节
        if(in.readableBytes() >= 8){
            out.add(in.readLong());
        }
    }

}
