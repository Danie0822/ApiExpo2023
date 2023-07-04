package com.example.expo.Models;

import lombok.Data;

@Data
public class LlegadasTarde {
    int idLlegadaTarde;
    int idTipoLlegadaTarde;
    int idEstudiante;
    int idPeriodo;
    int idDocente;
    int  estado;
    String Fecha;

    public LlegadasTarde(int idLlegadaTarde, int idTipoLlegadaTarde, int idPersona, int idPeriodo, int idDocente, int estado, String Fecha) {
        this.idLlegadaTarde = idLlegadaTarde;
        this.idTipoLlegadaTarde= idTipoLlegadaTarde;
        this.idEstudiante = idPersona;
        this.idPeriodo = idPeriodo;
        this.idDocente = idDocente;
        this.estado = estado;
        this.Fecha = Fecha;
    }


}

