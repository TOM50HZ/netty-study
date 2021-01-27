package com.fthz.netty.inboundandouthound;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class MyClientHandler extends SimpleChannelInboundHandler<Long> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Long msg) throws Exception {
        System.out.println("服务器的IP=" + ctx.channel().remoteAddress());
        System.out.println("收到服务器消息=" + msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //发送数据
        System.out.println("MyClientHandler 发送数据");
        ctx.writeAndFlush(123456L);
        // 这里有16个字节，前一个处理器是MyLongToByteEncoder
        // 前一个处理器是MyLongToByteEncoder父类是 MessageToByteEncoder write方法 有if (acceptOutboundMessage(msg))
//        ctx.writeAndFlush(Unpooled.copiedBuffer("abcdabcdabcdabcd", CharsetUtil.UTF_8));

    }
}
