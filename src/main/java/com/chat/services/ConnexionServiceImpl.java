package com.chat.services;

import com.chat.dao.ConnexionDao;
import com.chat.dao.ConnexionDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConnexionServiceImpl implements ConnexionService {

    private static final int PSEUDO_SIZE = 20;

    @Autowired
    ConnexionDaoImpl connexionDao;

    public String connect(String pseudo) throws Exception {
        if (pseudo.length() > PSEUDO_SIZE)
            throw new Exception();
        if (ConnexionDaoImpl.getConnectedPersons().contains(pseudo)) {
            throw new Exception();
        }
        connexionDao.setConnectedPerson(pseudo);
        ConnexionDaoImpl.getConnectedPersons().add(pseudo);
        return pseudo;
    }

    public void disconnect() {
        ConnexionDaoImpl.getConnectedPersons().remove(connexionDao.getConnectedPerson());
        connexionDao.setConnectedPerson("");
    }

}
