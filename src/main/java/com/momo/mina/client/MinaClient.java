package com.momo.mina.client;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
public class MinaClient {
    private static final String HOST="127.0.0.1";
    private static final int PORT=8080;
    public static void main(String[] args) throws InterruptedException {
        NioSocketConnector connector=new  NioSocketConnector();
        connector.setConnectTimeoutMillis(30000);
        connector.getFilterChain().addLast(
                "codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
        connector.getSessionConfig().setReceiveBufferSize(10240);// 设置接收缓冲区的大小
        connector.getSessionConfig().setSendBufferSize(10240);// 设置发送缓冲区的大小
        connector.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 30000);
        connector.setHandler( new MsgClientHandler());
        ConnectFuture cf = connector.connect(new InetSocketAddress(HOST, PORT));// 建立连接
        cf.awaitUninterruptibly();// 等待连接创建完成
        for (int i=0;i<10;i++) {
            cf.getSession().write("Hello TimeServer");
            Thread.sleep(1000);
        }
    }
}
