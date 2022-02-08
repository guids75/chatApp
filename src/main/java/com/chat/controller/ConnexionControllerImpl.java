package com.chat.controller;

import com.chat.exception.PseudoAlreadyTakenException;
import com.chat.exception.PseudoTooLongException;
import com.chat.service.ConnexionServiceImpl;
import com.chat.websocket.ClientMessageWebSocket;
import com.chat.websocket.ServerMessageWebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Controller
public class ConnexionControllerImpl {

    @Autowired
    private ConnexionServiceImpl connexionService;

    public void newConnexionAlert(String name) {
        WebSocketContainer wsContainer =
                ContainerProvider.getWebSocketContainer();
        try {
            Session wsSession = wsContainer.connectToServer(
                    ClientMessageWebSocket.class,
                    new URI("ws://localhost:8080/message"));
            MessageHandler handler = new MessageHandler() {
                @Override
                public int hashCode() {
                    return super.hashCode();
                }
            };
            wsSession.addMessageHandler(handler);
            wsSession.getBasicRemote().sendText("User " + name + " just connected");

        } catch (DeploymentException | URISyntaxException | IOException e) {
            System.out.println("Problem encountered : " + e);
        }
    }

    @PostMapping(value = "/connect")
    public String connect(@RequestParam String name) throws PseudoAlreadyTakenException, PseudoTooLongException {
        newConnexionAlert(name);
        return connexionService.connect(name);
    }

    @MessageMapping("/chat")
    // Sends the return value of this method to /topic/messages
    @SendTo("/topic/messages")
    public String getMessages(String message){
        System.out.println("hereeeeeeee");
        return message;
    }

}
