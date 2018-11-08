package com.singgel.nettyChat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Author: hekuangsheng
 * @Date: 2018/11/8
 */
public class Client {
    public static void main(String[] args) throws InterruptedException, IOException {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientInitializer());

            Channel channel = bootstrap.connect("localhost", 8877).sync().channel();

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            for(;;){
                channel.writeAndFlush(reader.readLine() + "\n");
            }
        }finally{
            eventLoopGroup.shutdownGracefully(); //优雅关闭
        }
    }
}
