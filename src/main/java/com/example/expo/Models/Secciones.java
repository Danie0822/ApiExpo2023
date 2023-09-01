package com.example.expo.Models;

import lombok.Data;

@Data
public class Secciones {
    int idSeccion;
    String seccion;

    public Secciones(int idSeccion, String seccion) {
        this.idSeccion = idSeccion;
        this.seccion = seccion;
    }

    public Secciones(){

    }
}
