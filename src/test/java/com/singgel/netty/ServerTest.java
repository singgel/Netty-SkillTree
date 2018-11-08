package com.singgel.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Author: hekuangsheng
 * @Date: 2018/11/8
 *
 * Netty的启动服务程序
 */
public class ServerTest {
    public static void main(String[] args) throws InterruptedException {
        /**
         * bossGroup, 父类的事件循环组只是负责连接，获取到连接后交给 workergroup子的事件循环组，
         * 参数的获取，业务的处理等工作均是由workergroup这个子事件循环组来完成，一个事件循环组一样
         * 可以完成所有的工作，但是Netty推荐的方式是使用两个事件循环组。
         */
        EventLoopGroup bossGroup = new NioEventLoopGroup();  //创建父事件循环组
        EventLoopGroup workerGroup = new NioEventLoopGroup(); //创建子类的事件循环组

        try{
            //创建启动服务器的对象
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            /**
             * group方法接收两个参数， 第一个为父时间循环组，第二个参数为子事件循环组
             */
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)  //bossGroup的通道，只是负责连接
                    .childHandler(new TestChannelnitializer()); //workerGroup的处理器，

            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();  //绑定端口
            channelFuture.channel().closeFuture().sync();

        }finally{
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
