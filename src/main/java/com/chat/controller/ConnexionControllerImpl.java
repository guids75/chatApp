package com.chat.controller;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

@Controller
public class ConnexionControllerImpl {

    public String newConnexionAlert(String name) {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = "http://localhost:8080/message";
        HttpEntity<String> request = new HttpEntity<>("The user " + name + " just connected");
        return restTemplate.postForObject(fooResourceUrl, request, String.class);
    }
}
