package com.momo.mina.server;

import com.momo.mina.server.TimeServerHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

public class MinaTimeServer {
    private static final int PORT=8080;
    public static void main(String[] args) throws IOException {
        NioSocketAcceptor acceptor=new NioSocketAcceptor();
        acceptor.getFilterChain().addLast(
                "codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
        acceptor.getSessionConfig().setReadBufferSize(2048);
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
        acceptor.setHandler(new TimeServerHandler());
        acceptor.bind(new InetSocketAddress(PORT));
    }
}
