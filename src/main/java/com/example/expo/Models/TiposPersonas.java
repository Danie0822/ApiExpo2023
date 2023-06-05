package com.example.expo.Models;

import java.util.List;

public class TiposPersonas {
    private int idTipoPersona;

    private String tipoPersona;

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public int getIdTipoPersona() {
        return idTipoPersona;
    }

    public void setIdTipoPersona(int idTipoPersona) {
        this.idTipoPersona = idTipoPersona;
    }

    public TiposPersonas(int idTipoPersona, String tipoPersona) {
        this.idTipoPersona = idTipoPersona;
        this.tipoPersona = tipoPersona;

    }


    public TiposPersonas() {
    }

    



    public void add(List<TiposPersonas> TiposPersonas) {
    }

    
}
