package com.fthz.netty.codec;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufEncoder;

public class NettyClient {
    public static void main(String[] args) throws InterruptedException {

        //事件循环组
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup();

       try{
           //客户端启动对象
           Bootstrap bootstrap = new Bootstrap();

           bootstrap.group(eventExecutors) //设置线程组
                   .channel(NioSocketChannel.class) //客户端通道
                   .handler(new ChannelInitializer<SocketChannel>() {
                       @Override
                       protected void initChannel(SocketChannel ch) throws Exception {
                           ChannelPipeline pipeline = ch.pipeline();
                           pipeline.addLast("encoder", new ProtobufEncoder());
                           pipeline.addLast(new NettyClientHandler()); //加入Handler
                       }
                   });

           System.out.println("Client is Ready");

           //启动客户端连接服务器端
           ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 6668).sync();
           channelFuture.channel().closeFuture().sync();
       } finally {
           eventExecutors.shutdownGracefully();
       }
    }
}
