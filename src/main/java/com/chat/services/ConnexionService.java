package com.chat.services;

import com.chat.exception.PseudoAlreadyTakenException;
import com.chat.exception.PseudoTooLongException;

public interface ConnexionService {

    String connect(String pseudo) throws PseudoTooLongException, PseudoAlreadyTakenException;

    void disconnect();

}
