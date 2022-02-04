package com.chat.services;

import com.chat.dao.ConnexionDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    ConnexionDaoImpl connexionDao;

    public void sendMessage(String message) {
        for (String person : ConnexionDaoImpl.getConnectedPersons()) {
            if (!person.equals(connexionDao.getConnectedPerson())) {

            }
        }
    }

}
