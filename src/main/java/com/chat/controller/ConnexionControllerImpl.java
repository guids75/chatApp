package com.chat.controller;

import com.chat.websocket.ServerMessageWebSocket;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.WebSocketContainer;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Controller
public class ConnexionControllerImpl {

    public String newConnexionAlert(String name) {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        try {
            container.connectToServer(ServerMessageWebSocket.class, new URI("ws://localhost:8080/message"));
        } catch (DeploymentException | URISyntaxException | IOException e) {
            System.out.println("Problem encountered : " + e);
        }

        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = "http://localhost:8080/message";
        HttpEntity<String> request = new HttpEntity<>("The user " + name + " just connected");
        return restTemplate.postForObject(fooResourceUrl, request, String.class);
    }
}
