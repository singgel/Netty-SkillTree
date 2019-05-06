package com.hks.server;

/**
* @Title: NettyClientApp
* @Description: Netty 服务端主程序
* @Version:1.0.0  
* @author hekuangsheng
* @date 2018年7月11日
*/
public class NettyServerApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		NettyServer nettyServer=new NettyServer();
		nettyServer.run();
	}

}
