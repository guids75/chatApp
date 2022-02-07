package com.chat.controller;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

@Controller
public class MessageControllerImpl {

    public String sendMessage(String message) {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = "http://localhost:8080/message";
        HttpEntity<String> request = new HttpEntity<>(message);
        return restTemplate.postForObject(fooResourceUrl, request, String.class);
    }

}
