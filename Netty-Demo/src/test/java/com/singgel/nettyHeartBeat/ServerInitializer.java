package com.singgel.nettyHeartBeat;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @Author: hekuangsheng
 * @Date: 2018/11/8
 */
public class ServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        /**
         * IdleStateHandler: 空闲状态处理器。
         * 四个参数：1.读空闲； 2.写空闲；3.读写空闲； 4.时间单位。
         * 所谓的空闲是指多长时间没有发生过对应的时间，就触发调用.
         */
        pipeline.addLast(new IdleStateHandler(5, 7, 3, TimeUnit.SECONDS));
        pipeline.addLast(new ServerHandler());
    }
}