package com.example.expo.Models;

import lombok.Data;

@Data
public class ObservacionesString {
    int idObservacion;
    String Estudiante;
    String Docente;
    int idPeriodo;
    String fecha;
    String detalle;
    int idEstudiante;

    public ObservacionesString(int idObservacion, String Estudiante, String Docente, int idPeriodo, String fecha, String detalle, int idEstudiante) {
        this.idObservacion = idObservacion;
        this.Estudiante = Estudiante;
        this.Docente = Docente;
        this.idPeriodo = idPeriodo;
        this.fecha = fecha;
        this.detalle = detalle;
        this.idEstudiante = idEstudiante;
    }
    
    
}
