package com.singgel.nettyMulitProtobuf;

import com.singgel.nettyMulitProtobuf.protobuf.DataInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author: hekuangsheng
 * @Date: 2018/11/9
 */
public class ServerHandler extends SimpleChannelInboundHandler<DataInfo.Datas> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataInfo.Datas msg) throws Exception {
        /**
         * 因为最先写过来的是Person
         */
        DataInfo.Person p = msg.getPerson();

        System.out.println(msg.getDataType().toString());
        System.out.println(p.getId());
        System.out.println(p.getGender().toString());
        System.out.println(p.getName());

        DataInfo.Datas data = DataInfo.Datas.newBuilder()
                .setDataType(DataInfo.Datas.DataType.dogType)
                .setDog(
                        DataInfo.Dog.newBuilder()
                                .setAge(23)
                                .setColor("红色")
                                .setHeight(3.5f)
                ).build();
        ctx.writeAndFlush(data);
    }
}