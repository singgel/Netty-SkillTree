package com.singgel.nettyChat;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * @Author: hekuangsheng
 * @Date: 2018/11/8
 */
public class ChartServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        /**
         * DelimiterBasedFrameDecoder: 基于分隔符的帧解码器。
         * 两个参数的含义分别为：
         *     1.帧数据的最大长度。
         *  2.以XX作为分隔符, Delimiters.lineDelimiter()表示一\r\n或者\n作为分割符号。
         *  例如一下字符串：
         *  +--------------+
         *  | ABC\nDEF\r\n |
         *  +--------------+
         *  解码后的字符串为:
         *  +-----+-----+
         *  | ABC | DEF |
         *  +-----+-----+
         *  而不是：
         *  +----------+
         *  | ABC\nDEF |
         *  +----------+
         */
        pipeline.addLast("delimiterBasedFrameDecoder", new DelimiterBasedFrameDecoder(4096, Delimiters.lineDelimiter()));
        pipeline.addLast("stringDecoder", new StringDecoder(CharsetUtil.UTF_8));  //编码不指定，默认为utf-8
        pipeline.addLast("stringEncoder", new StringEncoder(CharsetUtil.UTF_8));
        pipeline.addLast(new ChartServerHandler());
    }
}
