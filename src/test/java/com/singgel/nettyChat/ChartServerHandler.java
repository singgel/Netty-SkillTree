package com.singgel.nettyChat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @Author: hekuangsheng
 * @Date: 2018/11/8
 */
public class ChartServerHandler extends SimpleChannelInboundHandler<String> {

    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channelGroup.size());
        channelGroup.forEach(c -> {
            if(channel == c){  //如果自己
                channel.writeAndFlush("【自己】: " + msg + "\n");
            }else{ //发送给其他人的信息
                c.writeAndFlush(channel.remoteAddress() + ": " + msg + "\n");
            }
        });
    }

    //连接建立
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel(); //获取到上线的连接

        channelGroup.writeAndFlush("【服务器】: " + channel.remoteAddress() + "上线\n"); //服务器发送广播通知

        channelGroup.add(channel);  //将通道添加到组
    }

    //连接断开
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("【服务器】: " + channel.remoteAddress() + "下线了\n");
    };

    //连接激活
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + "上线了");
    }

    //连接断开
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + "下线了");
    };

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
