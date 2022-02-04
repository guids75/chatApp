package com.chat.application;

import com.chat.services.ConnexionService;
import com.chat.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
        in = new Scanner(System.in);
        connect();
    }

    private void menu() {
        System.out.println("Taper \"send\" pour envoyer un message ou \"disconnect\" pour vous déconnecter :");
        switch(in.nextLine()) {
            case "send" :
                send();
                menu();
                break;
            case "disconnect" :
                disconnect();
                break;
            default :
                System.out.println("Il semble que le mot tapé soit erronné, veuillez recommencer");
                menu();
                break;
        }
    }

    private void connect() throws Exception {
        System.out.println("Veuillez définir votre pseudo (20 caractères maximum) :");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        System.out.println(s + "sssss");
        connexionService.connect(in.next());
        menu();
    }

    private void disconnect() {
        connexionService.disconnect();
        in.close();
    }

    private void send() {
        System.out.println("Veuillez taper le message à envoyer aux autres utilisateurs :");
        messageService.sendMessage(in.next());
    }
}
