package com.chat.dao;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ConnexionDaoImpl implements ConnexionDao {

    private static List<String> connectedPersons = new ArrayList<>();
    private String connectedPerson;

    public static List<String> getConnectedPersons() {
        return connectedPersons;
    }

    public String getConnectedPerson() {
        return connectedPerson;
    }

    public void setConnectedPerson(String connectedPerson) {
        this.connectedPerson = connectedPerson;
    }
}
