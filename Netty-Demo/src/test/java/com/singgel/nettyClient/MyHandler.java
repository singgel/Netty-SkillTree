package com.singgel.nettyClient;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author: hekuangsheng
 * @Date: 2018/11/8
 */
public class MyHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + ":" + msg);
        ctx.channel().writeAndFlush("from server: hks");
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println(System.currentTimeMillis() + "********");
        System.out.println("server handler added**********");
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println(System.currentTimeMillis() + "********");
        System.out.println("server channel register****");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(System.currentTimeMillis() + "********");
        System.out.println("server channel actieve****");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
