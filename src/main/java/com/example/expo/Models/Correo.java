package com.example.expo.Models;

import lombok.Data;

@Data
public class Correo {
    String Correo;
    String Code;

    public Correo(String correo, String code) {
        Correo = correo;
        Code = code;
    }
}
