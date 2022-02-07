package com.chat.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import java.util.logging.Logger;

@Component
@ClientEndpoint
public class ClientMessageWebSocket {

    private static final Logger logger = Logger.getLogger(ClientMessageWebSocket.class.getName());

    @OnOpen
    public void onOpen(Session session) {
        logger.info("OnOpen invoked by Session " + session.getId());
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        logger.info("OnClose invoked by Session " + session.getId() + " : reason is " + closeReason.getReasonPhrase());
    }

    @OnMessage
    public void onMessage(Session session, String msg) {
        logger.info("Message sent by " + session.getId() + " \nMessage is " + msg);
    }
}