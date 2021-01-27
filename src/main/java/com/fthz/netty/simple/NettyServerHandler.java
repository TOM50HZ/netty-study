package com.fthz.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;


public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    //读取客户端发送的消息
    /*
    *  ctx中有
    * 管道：Handler处理
    * 通道：数据处理
    * 地址
    * msg就是客户端发送的数据
    * */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {


//        // super.channelRead(ctx, msg);
//        System.out.println("Server ctx = " + ctx);
//        ByteBuf buf = (ByteBuf) msg;
//
//        Channel channel = ctx.channel(); //通道
//        ChannelPipeline pipeline = ctx.pipeline(); //管道，底层是双向链表
//        System.out.println("客户端发送的消息是：" + buf.toString(CharsetUtil.UTF_8));
//        System.out.println("客户端地址：" + channel.remoteAddress());


        //如果这里有一个费时业务 -> 异步执行 ->提交该channel对应的 NIOEventLoop的taskQueue中

        //解决方法一：用户自定义普通任务
        ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10 * 1000);
                    ctx.writeAndFlush(Unpooled.copiedBuffer("Hello,客户端2",CharsetUtil.UTF_8)); //写入缓冲并刷新，编码并发送消息
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        //这个需要30S之后客户端才会收到回复，因为它在任务队列里的第二个任务
        ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(20 * 1000);
                    ctx.writeAndFlush(Unpooled.copiedBuffer("Hello,客户端3",CharsetUtil.UTF_8)); //写入缓冲并刷新，编码并发送消息
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        //用户自定义定时任务，将会提交到scheduleTackQueue中
        ctx.channel().eventLoop().schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5 * 1000);
                    ctx.writeAndFlush(Unpooled.copiedBuffer("Hello,客户端4",CharsetUtil.UTF_8)); //写入缓冲并刷新，编码并发送消息
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 5, TimeUnit.SECONDS);
        System.out.println("go on...");
    }

    //数据读取完毕
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // super.channelReadComplete(ctx);
        ctx.writeAndFlush(Unpooled.copiedBuffer("Hello,客户端1",CharsetUtil.UTF_8)); //写入缓冲并刷新，编码并发送消息
    }

    //发生异常
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //super.exceptionCaught(ctx, cause);
        ctx.close();
    }
}
