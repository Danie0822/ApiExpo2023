package com.example.expo.Models;

import lombok.Data;

@Data
public class Periodos {
    int idPeriodo;
    String inicio;
    String finals;


    public Periodos(int idPeriodo, String inicio, String finals) {
        this.idPeriodo = idPeriodo;
        this.inicio = inicio;
        this.finals = finals;
    }
}
