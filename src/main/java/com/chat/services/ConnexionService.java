package com.chat.services;

public interface ConnexionService {

    String connect(String pseudo) throws Exception;

    void disconnect();

}
