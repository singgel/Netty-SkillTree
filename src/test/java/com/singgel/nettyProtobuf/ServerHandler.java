package com.singgel.nettyProtobuf;

import com.singgel.protocolBuffer.protobuf.AddressBookProtos;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.List;

/**
 * @Author: hekuangsheng
 * @Date: 2018/11/9
 */
public class ServerHandler extends SimpleChannelInboundHandler<AddressBookProtos.AddressBook> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, AddressBookProtos.AddressBook msg) throws Exception {
        List<AddressBookProtos.Person> list = msg.getPeopleList();
        list.forEach(p -> System.out.println(p.getName() + ";;" + p.getId() + ";;" + p.getEmail()));
    }
}