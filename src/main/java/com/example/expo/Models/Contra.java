package com.example.expo.Models;

import lombok.Data;

@Data
public class Contra {
    int idPersona;
    String claveCredenciales;

    public Contra(int idPersona, String claveCredenciales) {
        this.idPersona = idPersona;
        this.claveCredenciales = claveCredenciales;
    }
}
