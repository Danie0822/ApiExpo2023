package com.example.expo.Models;

import lombok.Data;

@Data
public class LlegadasTardeString {

    int idLlegadaTarde;
    String TipoLlegadaTarde;
    String Estudiante;
    int idPeriodo;
    String Docente;
    int estado;
    String fecha;
    int idEstudiante;

    public LlegadasTardeString(int idLlegadaTarde, String TipoLlegadaTarde, String Estudiante, int idPeriodo, String Docente, int estado, String fecha, int idEstudiante) {
        this.idLlegadaTarde = idLlegadaTarde;
        this.TipoLlegadaTarde = TipoLlegadaTarde;
        this.Estudiante = Estudiante;
        this.idPeriodo = idPeriodo;
        this.Docente = Docente;
        this.estado = estado;
        this.fecha = fecha;
        this.idEstudiante = idEstudiante;
    }
    
    
}
