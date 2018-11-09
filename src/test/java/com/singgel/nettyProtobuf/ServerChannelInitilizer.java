package com.singgel.nettyProtobuf;

import com.singgel.protocolBuffer.protobuf.AddressBookProtos;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

/**
 * @Author: hekuangsheng
 * @Date: 2018/11/9
 */
public class ServerChannelInitilizer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        /**
         * 采用Base 128 Varints进行编码，在消息头上加上32个整数，来标注数据的长度。
         */
        pipeline.addLast("protobufVarint32FrameDecoder", new ProtobufVarint32FrameDecoder());
        pipeline.addLast("protobufDecoder", new ProtobufDecoder(AddressBookProtos.AddressBook.getDefaultInstance()));

        /**
         * 对采用Base 128 Varints进行编码的数据解码
         */
        pipeline.addLast("protobufVarint32LengthFieldPrepender", new ProtobufVarint32LengthFieldPrepender());
        pipeline.addLast("protobufEncoder", new ProtobufEncoder());


        pipeline.addLast("serverHandler", new ServerHandler());
    }
}