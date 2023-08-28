package com.example.expo.Models;

import lombok.Data;

@Data
public class VisitasEnfermeria {

     int idVisitaEnfermeria;
     String idPersona;
     int idPeriodo;
     String fecha;
     String detalleVisitia;

    public VisitasEnfermeria(int idVisitaEnfermeria, String idPersona, int idPeriodo, String fecha, String detalleVisitia) {
        this.idVisitaEnfermeria = idVisitaEnfermeria;
        this.idPersona = idPersona;
        this.idPeriodo = idPeriodo;
        this.fecha = fecha;
        this.detalleVisitia = detalleVisitia;
    }
}
