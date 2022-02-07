package com.chat.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@ServerEndpoint("/message")
public class ServerMessageWebSocket {

    private static final Logger logger = Logger.getLogger(ServerMessageWebSocket.class.getName());
    static Queue<Session> queue = new ConcurrentLinkedQueue<>();

    public static void send(String message, String sessionId) {
        try {
            for (Session session : queue) {
                if (!sessionId.equals(session.getId())) {
                    session.getBasicRemote().sendText(message);
                    logger.log(Level.INFO, "Message sent by " + sessionId + " : " +
                            message + " to " + session.getId());
                }
            }
        } catch (IOException e) {
            logger.log(Level.WARNING, e.toString());
        }
    }

    @OnOpen
    public void open(Session session) {
        queue.add(session);
    }

    @OnClose
    public void close(Session session) {
        queue.remove(session);
    }

    @OnError
    public void error(Session session, Throwable t) {
        queue.remove(session);
    }

}
