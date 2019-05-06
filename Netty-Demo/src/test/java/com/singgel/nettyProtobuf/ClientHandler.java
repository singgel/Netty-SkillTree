package com.singgel.nettyProtobuf;

import com.singgel.protocolBuffer.protobuf.AddressBookProtos;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author: hekuangsheng
 * @Date: 2018/11/9
 */
public class ClientHandler extends SimpleChannelInboundHandler<AddressBookProtos.AddressBook> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, AddressBookProtos.AddressBook msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        AddressBookProtos.AddressBook ab = AddressBookProtos.AddressBook.newBuilder()
                .addPeople(AddressBookProtos.Person.newBuilder().setEmail("123@qq.com").setId(23).setName("zhangsan"))
                .addPeople(AddressBookProtos.Person.newBuilder().setEmail("789@163.com").setId(45).setName("lisi"))
                .build();

        ctx.writeAndFlush(ab);
    }
}