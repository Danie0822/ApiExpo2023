package com.example.expo.Models;

import lombok.Data;

@Data
public class CredeInsertUpdate {
    int idPersona;
    String codigo;
    String nombrePersona;
    String apellidoPersona;
    String nacimientoPersona;
    String idTipoPersona;
    String correo;
    String claveCredenciales;
    byte[] foto;
    String telefonoPersona;

    public CredeInsertUpdate (int idPersona, String codigo,String nombrePersona, String apellidoPersona, String nacimientoPersona,
                              String idTipoPersona, String correo,String claveCredenciales,byte[] foto, String telefonoPersona){
        this.idPersona = idPersona;
        this.codigo = codigo;
        this.nombrePersona = nombrePersona;
        this.apellidoPersona = apellidoPersona;
        this.nacimientoPersona = nacimientoPersona;
        this.idTipoPersona = idTipoPersona;
        this.correo = correo;
        this.claveCredenciales = claveCredenciales;
        this.foto = foto;
        this.telefonoPersona = telefonoPersona;
    }
}
