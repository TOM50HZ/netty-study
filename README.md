# README

这是观看Netty时的学习笔记

视频地址：https://www.bilibili.com/video/BV1DJ411m7NR



## 目录

## 1 IO模型

### [1.1 BIO简介](https://blog.csdn.net/baidu_39414557/article/details/113057386)

+ 示例位置：
  + com.fthz.bio
    + BIOServer：传统阻塞型IO演示

### [1.2 NIO简介](https://blog.csdn.net/baidu_39414557/article/details/113057598)

#### [1.2.1 Buffer 机制](https://blog.csdn.net/baidu_39414557/article/details/113057667)

+ 示例位置：
  + com.fthz.nio
    + BasicBuffer：简单的Buffer读写操作
    + NIOByteBufferPutGet：可类型化的Buffer
    + mappedByteBuffer：可直接修改内存的Buffer
    + ReadOnlyBuffer：只读的Buffer
    + ScatteringNGatheringTest：Buffert的分散与聚合

#### [1.2.2 Channel 通道与 Selector选择器](https://blog.csdn.net/baidu_39414557/article/details/113057868)

+ 示例位置
  + com.fthz.nio
    + FileChannel01：通道输出示例
    + FileChannel02：通道输入示例
    + FileChannel03：使用同一个Buffer和两个管道完成拷贝
    + FileChannel04：使用两个管道拷贝文件

#### [1.2.4 NIO入门示例](https://blog.csdn.net/baidu_39414557/article/details/113058091)

+ 示例位置
  + com.fthz.nio
    + NIOServe：服务端
    + NIOClient：客户端

#### [1.2.5 NIO入门实战群聊系统](https://blog.csdn.net/baidu_39414557/article/details/113058102)

+ 示例位置：
  + com.fthz.nio.groupchat
    + GroupChatServer：群聊服务端
    + GroupChatClient：群聊客户端



## 2 Netty

### [2.1 Netty产生原因](https://blog.csdn.net/baidu_39414557/article/details/113058129)

### [2.2 线程模型介绍](https://blog.csdn.net/baidu_39414557/article/details/113058176)

### [2.3 Netty快速入门](https://blog.csdn.net/baidu_39414557/article/details/113058465)

+ 示例位置：
  + com.fthz.netty.simple
    + NettyServer：服务端
    + NettyServerHandler：服务端的自定义处理器
    + NettyClient：客户端
    + NettyClientHandler：客户端自定义处理器

### [2.4 TaskQueue自定义任务](https://blog.csdn.net/baidu_39414557/article/details/113058493)

+ 示例位置: com.fthz.netty.simple.NettyServerHandler：注释部分，包含用户自定义任务和用户自定义定时任务

### [2.5 异步模型与HTTP服务程序示例](https://blog.csdn.net/baidu_39414557/article/details/113058516)

+ 示例位置：
  + com.fthz.netty.http
    + TestServer：服务器
    + TestHttpServerHandler：服务器自定义处理器
    + TestHttpServerInitializer：服务器的初始化器

### [2.6 核心模块概述](https://blog.csdn.net/baidu_39414557/article/details/113058689)

### [2.7 Unlooped类](https://blog.csdn.net/baidu_39414557/article/details/113058718)

+ 示例位置
  + com.fthz.netty.buf
  
    + NettyByteBuf01：简单读写示例
  
    + NettyByteBuf02：一些属性

### [2.8 实战 群聊系统](https://blog.csdn.net/baidu_39414557/article/details/113058744)

+ 示例位置
  + com.fthz.netty.groupchat
    + GroupServer：服务端
    + GroupServerHandler：服务端自定义处理器
    + GroupClient：群聊客户端
    + GroupClientHandler：群聊客户端自定义处理器

### [2.9 心跳机制](https://blog.csdn.net/baidu_39414557/article/details/113058762)

+ 示例位置
  + com.fthz.netty.heartbeat
    + MyServer：服务端
    + MyServerHandler：服务端自定义自定义处理器

### [2.10 WebSocket长连接开发](https://blog.csdn.net/baidu_39414557/article/details/113058817)

+ 示例位置
  + com.fthz.netty.websocket
    + MyServer：服务端
    + MyTextWebSocketFrameHandler：服务端自定义处理器
    + hello.html：客户端

### [2.11 编码与解码器](https://blog.csdn.net/baidu_39414557/article/details/113058848)

+ Codec入门示例位置
  + com.fthz.netty.codec
    + NettyClient：客户端
    + NettyClientHandler：客户端自定义处理器
    + NettyServer：服务端
    + NettyServerHandler：服务端自定义处理器
    + Student.proto：protobuf配置文件
    + StudentPOJO：使用protobuf生成的实体类
+ ProtoBuf传输多种类型示例位置
  + com.fthz.netty.codec
    + NettyClient：客户端
    + NettyClientHandler：客户端自定义处理器
    + NettyServer：服务端
    + NettyServerHandler：服务端吃力气
    + Student.proto：protobuf配置文件
    + MyDataInfo：使用protobuf生成的实体类

### [2.12 入站出站机制](https://blog.csdn.net/baidu_39414557/article/details/113058865)

+ 示例位置:
  + com.fthz.netty.inboundandouthound
    + MyByteToLongDecoder：Byte 转 Long 解码器
    + MyByteToLongDecoder2：继承了ReplayingDecoder的Byte 转 Long 解码器
    + MyLongToByteEncoder：Long 转 Byte 编码器
    + MyClient：客户端
    + MyClientHandler：客户端自定义处理器
    + MyClientInitializer：客户端初始化器
    + MyServer：服务器
    + MyServerHandler：服务器自定义处理器
    + MyServerInitializer：服务器初始化器

### [2.13 粘包与拆包](https://blog.csdn.net/baidu_39414557/article/details/113058876)

+ 粘包示例位置：
  + com.fthz.netty.tcp
    + MyClient：客户端
    + MyClientHandler：客户端自定义处理器
    + MyClientInitializer：客户端初始化器
    + MyServer：服务端
    + MyServerHandler：服务端自定义处理器
    + MyServerInitializer：服务端初始化器
+ 解决方案示例位置
  + com.fthz.netty.protoltcp
    + MessageProtocol：自定义协议
    + MyMessageDecoder：自定义解码器
    + MyMessageEncoder：自定义编码器
    + MyClient：客户端
    + MyClientHandler：客户端自定义处理器
    + MyClientInitializer：客户端初始化器
    + MyServer：服务端
    + MyServerHandler：服务端自定义处理器
    + MyServerInitializer：服务端初始化器



