package com.example.expo.Models;

import lombok.Data;

import java.util.Date;

@Data
public class Encargados {
    int idEncargado;
    String nombrePersona;
    String apellidoPersona;
    int idPersona ;
    String correoEncargado;

    public Encargados(int idEncargado, String nombrePersona, String apellidoPersona, int idPersona, String correoEncargado) {
        this.idEncargado = idEncargado;
        this.nombrePersona = nombrePersona;
        this.apellidoPersona = apellidoPersona;
        this.idPersona = idPersona;
        this.correoEncargado = correoEncargado;
    }
}
