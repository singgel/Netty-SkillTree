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
 * 服务端通道初始化程序
 */
public class MyInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline =  ch.pipeline();
        /**
         * LengthFieldBasedFrameDecoder: 基于长度属性的帧解码器。
         * 客户端传递过来的数据格式为：
         *  BEFORE DECODE (14 bytes)         AFTER DECODE (14 bytes)
         *    +--------+----------------+      +--------+----------------+
         *    | Length | Actual Content |----->| Length | Actual Content |
         *    | 0x000C | "HELLO, WORLD" |      | 0x000C | "HELLO, WORLD" |
         *    +--------+----------------+      +--------+----------------+
         * 5个参数依次为：1.(maxFrameLength)每帧数据的最大长度.
         *              2.(lengthFieldOffset)length属性在帧中的偏移量。
         *           3.(lengthFieldLength)length属性的长度，需要与客户端 LengthFieldPrepender设置的长度一致，
         *               值的取值只能为1, 2, 3, 4, 8
         *           4.(lengthAdjustment)长度调节值, 当信息长度包含长度时候，用于修正信息的长度。
         *           5.(initialBytesToStrip)在获取真实的内容的时候，需要忽略的长度(通常就是length的长度)。
         *
         * 参考： http://blog.csdn.net/educast/article/details/47706599
         */
        pipeline.addLast("lengthFieldBasedFrameDecoder",
                new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 2, 0, 2));
        /**
         * LengthFieldPrepender: length属性在帧中的长度。只能为1,2,3,4,8。
         * 该值与对应的客户端(或者服务端)在解码时候使用LengthFieldBasedFrameDecoder中所指定的lengthFieldLength
         * 的值要保持一致。
         */
        pipeline.addLast("lengthFieldPrepender", new LengthFieldPrepender(3));
        //StringDecoder字符串的解码器, 主要用于处理编码格式
        pipeline.addLast("stringDecoder", new StringDecoder(CharsetUtil.UTF_8));
        //StringDecoder字符串的编码器，主要用于指定字符串的编码格式
        pipeline.addLast("stringEncoder", new StringEncoder(CharsetUtil.UTF_8));

        pipeline.addLast(new MyHandler()); //自定义的Handler
    }
}
