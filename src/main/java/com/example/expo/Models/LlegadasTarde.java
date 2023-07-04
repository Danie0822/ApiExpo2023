package com.example.expo.Models;

import lombok.Data;

@Data
public class LlegadasTarde {
    int idLlegadaTarde;
    int idTipoLlegadaTarde;
    int idPersona;
    int idPeriodo;
    int idDocente;
    String TiposLlegadasTarde;


    public LlegadasTarde(int idLlegadaTarde, int idTipoLlegadaTarde, int idPersona, int idPeriodo, int idDocente, String TiposLlegadasTarde) {
        this.idLlegadaTarde = idLlegadaTarde;
        this.idTipoLlegadaTarde= idTipoLlegadaTarde;
        this.idPersona = idPersona;
        this.idPeriodo = idPeriodo;
        this.idDocente = idDocente;
        this.TiposLlegadasTarde = TiposLlegadasTarde;
    }


}

