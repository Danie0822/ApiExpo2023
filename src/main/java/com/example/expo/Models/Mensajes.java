package com.example.expo.Models;

import lombok.Data;

@Data
public class Mensajes {
    String messages;

    public Mensajes(String messages) {
        this.messages = messages;
    }
}
