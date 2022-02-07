package com.chat.service;

import com.chat.controller.ConnexionControllerImpl;
import com.chat.dao.ConnexionDaoImpl;
import com.chat.exception.PseudoAlreadyTakenException;
import com.chat.exception.PseudoTooLongException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConnexionServiceImpl implements ConnexionService {

    private static final int PSEUDO_SIZE = 20;

    @Autowired
    ConnexionDaoImpl connexionDao;

    @Autowired
    ConnexionControllerImpl connexionController;

    public String connect(String pseudo) throws PseudoTooLongException, PseudoAlreadyTakenException {
        if (pseudo.length() > PSEUDO_SIZE)
            throw new PseudoTooLongException("Le pseudo est trop long");
        if (ConnexionDaoImpl.getConnectedPersons().contains(pseudo)) {
            throw new PseudoAlreadyTakenException("Le pseudo est deja pris");
        }
        connexionDao.setConnectedPerson(pseudo);
        ConnexionDaoImpl.getConnectedPersons().add(pseudo);
        //connexionController.newConnexionAlert(pseudo);
        return pseudo;
    }

    public void disconnect() {
        String connectedPerson = connexionDao.getConnectedPerson();
        ConnexionDaoImpl.getConnectedPersons().remove(connectedPerson);
        connexionDao.setConnectedPerson("");
    }

}
