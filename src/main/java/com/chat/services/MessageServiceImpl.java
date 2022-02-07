package com.chat.services;

import com.chat.controller.MessageControllerImpl;
import com.chat.dao.ConnexionDaoImpl;
import com.chat.websocket.ClientMessageWebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    ConnexionDaoImpl connexionDao;

    @Autowired
    MessageControllerImpl messageController;

    @Autowired
    ClientMessageWebSocket clientMessageWebSocket;

    public void sendMessage(String message) {
        messageController.sendMessage(message);
    }

}
