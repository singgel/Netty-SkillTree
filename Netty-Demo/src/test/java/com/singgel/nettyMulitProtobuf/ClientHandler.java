package com.singgel.nettyMulitProtobuf;

import com.singgel.nettyMulitProtobuf.protobuf.DataInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author: hekuangsheng
 * @Date: 2018/11/9
 */
public class ClientHandler extends SimpleChannelInboundHandler<DataInfo.Datas> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataInfo.Datas msg) throws Exception {
        /**
         * 服务端写回来的是dog
         */
        DataInfo.Dog dog = msg.getDog();

        System.out.println(msg.getDataType().toString());
        System.out.println(dog.getAge());
        System.out.println(dog.getColor());
        System.out.println(dog.getHeight());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        DataInfo.Datas data = DataInfo.Datas.newBuilder()
                .setDataType(DataInfo.Datas.DataType.personType)
                .setPerson(
                        DataInfo.Person.newBuilder()
                                .setId(23)
                                .setGender(DataInfo.Person.Gender.female)
                                .setName("zhangsan")
                )
                .build();

        ctx.writeAndFlush(data);
    }
}