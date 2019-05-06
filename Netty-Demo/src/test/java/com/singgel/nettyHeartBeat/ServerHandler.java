package com.singgel.nettyHeartBeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @Author: hekuangsheng
 * @Date: 2018/11/8
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent)evt;

            String idleType = null;
            switch(event.state()){
                case READER_IDLE:
                    idleType = "读空闲";
                    break;
                case WRITER_IDLE:
                    idleType = "写空闲";
                    break;
                case ALL_IDLE:
                    idleType = "读写空闲";
                    break;
            }

            System.out.println(ctx.channel().remoteAddress() + " " + idleType);

            ctx.channel().close();
        }
    }
}