package com.example.expo.Models;

import lombok.Data;

@Data
public class Inasisitenciastring {

    int idInasistencia;
    String TipoLlegadaTarde;
    String Estudiante;
    String Docente;
    int idPeriodo;
    int estado;
    String fecha;
    int idEstudiante;

    public Inasisitenciastring(int idInasistencia, String TipoLlegadaTarde, String Estudiante, String Docente, int idPeriodo, int estado, String fecha, int idEstudiante) {
        this.idInasistencia = idInasistencia;
        this.TipoLlegadaTarde = TipoLlegadaTarde;
        this.Estudiante = Estudiante;
        this.Docente = Docente;
        this.idPeriodo = idPeriodo;
        this.estado = estado;
        this.fecha = fecha;
        this.idEstudiante = idEstudiante;
    }
    
    
}
