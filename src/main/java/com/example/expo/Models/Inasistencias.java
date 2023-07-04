package com.example.expo.Models;

import lombok.Data;

@Data
public class Inasistencias {

    int idInasistencia;
    int idTipoInasistencia;
    int idEstudiante;
    int idDocente;
    int idPeriodo;
    int Estado;
    String Fecha;

    public Inasistencias(int idInasistencia, int idTipoInasistencia, int idPersona, int idDocente, int idPeriodo, int Estado, String Fecha){
        this.idInasistencia = idInasistencia;
        this.idTipoInasistencia = idTipoInasistencia;
        this.idEstudiante = idPersona;
        this.idDocente = idDocente;
        this.idPeriodo = idPeriodo;
        this.Estado = Estado;
        this.Fecha = Fecha;
    }
}
