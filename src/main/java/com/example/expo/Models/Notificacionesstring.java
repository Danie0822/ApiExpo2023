package com.example.expo.Models;

import lombok.Data;

@Data
public class Notificacionesstring {
    int idInasistencia;
    String detalle;
    String TipoNotificacion;
    int idPersona;

    public Notificacionesstring(int idInasistencia, String detalle, String TipoNotificacion, int idPersona) {
        this.idInasistencia = idInasistencia;
        this.detalle = detalle;
        this.TipoNotificacion = TipoNotificacion;
        this.idPersona = idPersona;
    }

    
}
