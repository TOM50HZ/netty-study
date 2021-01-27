package com.fthz.netty.http;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class TestServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {


        //向管道加入处理器
        ChannelPipeline pipeline = ch.pipeline();

        //加入httpServerCodec
        pipeline.addLast("myHttpServerCodec", new HttpServerCodec());

        //自定义处理器
        pipeline.addLast("myTestHttpServerHandler", new TestHttpServerHandler());

    }
}
