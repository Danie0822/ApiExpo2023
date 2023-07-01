package com.example.expo.Models;

import lombok.Data;

@Data
public class Observaciones {
    int idObservacion ;
    int idEstudiante;
    int idDocente;
    int idPeriodo;
    String fecha;
    String detalle;

    public Observaciones (int idObservacion,int idEstudiante,int idDocente,int idPeriodo,String fecha,String detalle)
    {
        this.idObservacion = idObservacion;
        this.idEstudiante = idEstudiante;
        this.idDocente = idDocente;
        this.idPeriodo = idPeriodo;
        this.fecha = fecha;
        this.detalle = detalle;
    }

}
