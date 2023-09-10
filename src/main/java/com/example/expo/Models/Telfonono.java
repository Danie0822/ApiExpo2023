package com.example.expo.Models;

import lombok.Data;

@Data
public class Telfonono {
    String Numero;
    String Code;

    public Telfonono(String numero, String code) {
        Numero = numero;
        Code = code;
    }
}
