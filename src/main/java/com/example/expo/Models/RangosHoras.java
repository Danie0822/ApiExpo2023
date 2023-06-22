package com.example.expo.Models;

import lombok.Data;

import java.time.DateTimeException;

@Data
public class RangosHoras {
    int idRangoHora;
    String titulo;
    String inicio;
    String finals;


    public RangosHoras(int idRangoHora, String titulo, String inicio, String finals) {
        this.idRangoHora = idRangoHora;
        this.titulo = titulo;
        this.inicio = inicio;
        this.finals = finals;
    }
}
