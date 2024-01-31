package com.example.restaurantadmin.service.email;

public interface EmailSender {
    void send(String to, String body);
}
