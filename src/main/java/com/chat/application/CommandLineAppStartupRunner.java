package com.chat.application;

import com.chat.exception.PseudoAlreadyTakenException;
import com.chat.exception.PseudoTooLongException;
import com.chat.services.ConnexionService;
import com.chat.services.MessageService;
import com.chat.websocket.ServerMessageWebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.WebSocketContainer;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {

    @Autowired
    ConnexionService connexionService;

    @Autowired
    MessageService messageService;

    private Scanner in;

    @Override
    public void run(String...args) throws Exception {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        try {
            container.connectToServer(ServerMessageWebSocket.class, new URI("ws://localhost:8080"));
        } catch (DeploymentException | URISyntaxException | IOException e) {
            System.out.println("Problem encountered : " + e);
        }
        in = new Scanner(System.in);
        connect();
    }

    private void menu() {
        System.out.println("Taper \"send\" pour envoyer un message ou \"disconnect\" pour vous deconnecter :");
        switch(in.next()) {
            case "send" :
                send();
                menu();
                break;
            case "disconnect" :
                disconnect();
                break;
            default :
                System.out.println("Il semble que le mot ne soit pas valide, veuillez recommencer");
                menu();
                break;
        }
    }

    private void connect() throws PseudoTooLongException, PseudoAlreadyTakenException {
        System.out.println("Veuillez definir votre pseudo (20 caracteres maximum) :");
        try {
            connexionService.connect(in.next());
            /* appel rest prevenir */
            menu();
        } catch (PseudoTooLongException e) {
            System.out.println("Ce pseudo est trop long");
            connect();
        } catch (PseudoAlreadyTakenException e) {
            System.out.println("Ce pseudo est deja pris");
            connect();
        }
    }

    private void disconnect() {
        connexionService.disconnect();
       in.close();
    }

    private void send() {
        System.out.println("Veuillez taper le message a envoyer aux autres utilisateurs :");
        messageService.sendMessage(in.next());
        System.out.println("Votre message a bien ete envoye");
    }
}
