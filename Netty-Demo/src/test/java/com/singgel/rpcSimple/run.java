package com.singgel.rpcSimple;

import com.singgel.rpcSimple.client.RpcImporter;
import com.singgel.rpcSimple.exporter.RpcExporter;
import com.singgel.rpcSimple.server.EchoService;
import com.singgel.rpcSimple.server.EchoServiceImpl;

import java.net.InetSocketAddress;

public class run {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        //创建异步发布服务端的线程并启动，用于接受PRC客户端的请求，根据请求参数调用服务实现类，返回结果给客户端
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                try{
                    RpcExporter.exporter("localhost", 8088);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
        //创建客户端服务代理类，构造RPC求情参数，发起RPC调用
        RpcImporter<EchoService> importer=new RpcImporter<EchoService>();
        EchoService echo = importer.importer(EchoServiceImpl.class, new InetSocketAddress("localhost",8088));
        System.out.println(echo.echo("Are u ok?"));
    }

}
