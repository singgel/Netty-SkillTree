package com.singgel.hadooprpc;

import org.apache.hadoop.ipc.VersionedProtocol;

import java.io.IOException;

interface ClientProtocol extends VersionedProtocol {
    // 版本号,默认情况下,不同版本号的 RPC Client 和 Server 之间不能相互通信
    public static final long versionID = 1L;
    String echo(String value) throws IOException;
    int add(int v1, int v2) throws IOException;
}
