package com.example.expo.Models;

import lombok.Data;

@Data
public class Comunicados {
    int idComunicado;
    int idGrado;
    String detalle;
    String fecha;
    byte[] archivo;

    public Comunicados(int idComunicado, int idGrado, String detalle, String fecha, byte[] archivo) {
        this.idComunicado = idComunicado;
        this.idGrado = idGrado;
        this.detalle = detalle;
        this.fecha = fecha;
        this.archivo = archivo;

    }
}
