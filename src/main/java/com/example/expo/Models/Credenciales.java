package com.example.expo.Models;

import lombok.Data;

import java.util.Date;

@Data
public class Credenciales {
    int idPersona;
    String codigo;
    String nombrePersona;
    String apellidoPersona;
    String nacimientoPersona;
    int idTipoPersona;
    String correo;
    String claveCredenciales;
    byte[] foto;
    String telefonoPersona;


    public Credenciales (int idPersona, String codigo,String nombrePersona, String apellidoPersona, String nacimientoPersona,
                         int idTipoPersona, String correo,String claveCredenciales,byte[] foto){
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
    public Credenciales (int idPersona, String codigo,String nombrePersona, String apellidoPersona, String nacimientoPersona,
                         int idTipoPersona, String correo,String claveCredenciales,byte[] foto, String telefonoPersona ){
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
        this.telefonoPersona = telefonoPersona;
    }

}
