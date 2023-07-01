package com.example.expo.Models;

import lombok.Data;

@Data
public class CodigosConductualesPersonas {
    int idCodigoConductualPersona;
    int idEstudiante;
    int idCodigoConductual;
    int idDocente;
    int idPeriodo;
    String fecha;

    public CodigosConductualesPersonas (int idCodigoConductualPersona,int idEstudiante, int idCodigoConductual,int idDocente,int idPeriodo,String fecha){
        this.idCodigoConductualPersona = idCodigoConductualPersona;
        this.idEstudiante = idEstudiante;
        this.idCodigoConductual = idCodigoConductual;
        this.idDocente  = idDocente;
        this.idPeriodo = idPeriodo;
        this.fecha = fecha;
    }
}
