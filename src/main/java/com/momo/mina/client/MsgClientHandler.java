package com.momo.mina.client;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class MsgClientHandler extends IoHandlerAdapter {
    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        super.messageReceived(session, message);
        System.out.println("Message Received=====>"+message);
    }
}
