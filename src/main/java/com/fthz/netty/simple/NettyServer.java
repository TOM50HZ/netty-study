package com.fthz.netty.simple;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {
    public static void main(String[] args) throws Exception {

        //创建BossGroup 和 WorkerGroup，都是无限循环
        //子线程NioEventLoop的默认个数是CPU核心数*2
        EventLoopGroup bossGroup = new NioEventLoopGroup(1); //只处理连接请求
        NioEventLoopGroup workerGroup = new NioEventLoopGroup(); //处理业务

        try{
            ServerBootstrap bootstrap = new ServerBootstrap(); //服务器启动对象

            bootstrap.group(bossGroup, workerGroup) //两个线程组
                    .channel(NioServerSocketChannel.class) //服务器通道
                    .option(ChannelOption.SO_BACKLOG, 128) //线程队列等待连接的个数
                    .childOption(ChannelOption.SO_KEEPALIVE, true) //保持活动连接状态
                    .childHandler(new ChannelInitializer<SocketChannel>() { //创建一个通道初始化对象
                        //给pipeline设置处理器
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new NettyServerHandler());
                        }
                    }); //给workerGroup 的 EventLoop设置处理器

            System.out.println("server is ready");

            ChannelFuture cf = bootstrap.bind(6668).sync(); //绑定端口并且同步，就是启动服务器

            //给cf注册监听器
            cf.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if(cf.isSuccess()){
                        System.out.println("监听 6668 成功");
                    } else {
                        System.out.println("监听失败");
                    }
                }
            });

            cf.channel().closeFuture().sync(); //对关闭通道进行监听
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
