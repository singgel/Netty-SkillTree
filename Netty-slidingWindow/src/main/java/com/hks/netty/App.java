package com.hks.netty;

import com.hks.netty.client.NettyClient;
import com.hks.netty.server.NettyServer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
    	//启动服务端和客户端
    	new Thread(() -> new NettyServer().run()).start();
    	new Thread(() -> new NettyClient().run()).start();
    	

    }
}
