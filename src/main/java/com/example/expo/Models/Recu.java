package com.example.expo.Models;

import lombok.Data;

@Data
public class Recu {
    int idPersona;
    int idTipoPersona;
    String correo;

    public Recu(int idPersona, int idTipoPersona, String correo) {
        this.idPersona = idPersona;
        this.idTipoPersona = idTipoPersona;
        this.correo = correo;
    }
}
