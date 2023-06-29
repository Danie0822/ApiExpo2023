package com.example.expo.Models;

import lombok.Data;

@Data
public class VisitasEnfermeriaString {
    int idVisitaEnfermeria;
    String Persona;
    int idPeriodo;
    String fecha;
    String detalleVisitia;
    int idPersona;

    public VisitasEnfermeriaString(int idVisitaEnfermeria, String Persona, int idPeriodo, String fecha, String detalleVisitia, int idPersona) {
        this.idVisitaEnfermeria = idVisitaEnfermeria;
        this.Persona = Persona;
        this.idPeriodo = idPeriodo;
        this.fecha = fecha;
        this.detalleVisitia = detalleVisitia;
        this.idPersona = idPersona;
    }
}
