package com.example.expo.Models;

import lombok.Data;

@Data
public class Recu {
    int idPersona;
    int idTipoPersona;
    String correo;
    String Telefono;

    public Recu(int idPersona, int idTipoPersona, String correo, String Telfonono ) {
        this.idPersona = idPersona;
        this.idTipoPersona = idTipoPersona;
        this.correo = correo;
        this.Telefono = Telfonono;
    }
}
