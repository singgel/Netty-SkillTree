package com.singgel.nettyClient;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * @Author: hekuangsheng
 * @Date: 2018/11/8
 *
 * 客户端通道初始化
 */
public class ClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline =  ch.pipeline();
        pipeline.addLast("lengthFieldBasedFrameDecoder",
                new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 2, 0, 2));
        pipeline.addLast("lengthFieldPrepender", new LengthFieldPrepender(3));
        pipeline.addLast("stringDecoder", new StringDecoder(CharsetUtil.UTF_8));
        pipeline.addLast("stringEncoder", new StringEncoder(CharsetUtil.UTF_8));

        pipeline.addLast(new MyClientHandler());
    }
}
