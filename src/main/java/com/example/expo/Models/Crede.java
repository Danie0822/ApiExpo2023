package com.example.expo.Models;

import lombok.Data;

@Data
public class Crede {
    private int idPersona;
    private String codigo;
    private String nombrePersona;
    private String apellidoPersona;
    private String nacimientoPersona;
    private int idTipoPersona;
    private String correo;
    private String claveCredenciales;
    private byte[] foto;

    public Crede(int idPersona, String codigo, String nombrePersona, String apellidoPersona, String nacimientoPersona, int idTipoPersona, String correo, String claveCredenciales, byte[] foto) {
        this.idPersona = idPersona;
        this.codigo = codigo;
        this.nombrePersona = nombrePersona;
        this.apellidoPersona = apellidoPersona;
        this.nacimientoPersona = nacimientoPersona;
        this.idTipoPersona = idTipoPersona;
        this.correo = correo;
        this.claveCredenciales = claveCredenciales;
        this.foto = foto;
    }
}
