package com.example.expo.Models;

import lombok.Data;

@Data
public class CodigosConductualesString {
    int idCodigoConductualPersona;
    String Estudiante;
    String CodigoConductual;
    String tipoCodigoConductual;
    String Docente;
    int idPeriodo;
    String fecha;
    int idEstudiante;

    public CodigosConductualesString(int idCodigoConductualPersona, String Estudiante, String CodigoConductual,String tipoCodigoConductual, String Docente, int idPeriodo, String fecha, int idEstudiante) {
        this.idCodigoConductualPersona = idCodigoConductualPersona;
        this.Estudiante = Estudiante;
        this.CodigoConductual = CodigoConductual;
        this.tipoCodigoConductual = tipoCodigoConductual;
        this.Docente = Docente;
        this.idPeriodo = idPeriodo;
        this.fecha = fecha;
        this.idEstudiante = idEstudiante;
    }
    
    

    
}
