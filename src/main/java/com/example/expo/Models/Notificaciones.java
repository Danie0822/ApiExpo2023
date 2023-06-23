package com.example.expo.Models;

import lombok.Data;

@Data
public class Notificaciones {
    int idNotificacion;
    String detalle;
    int idTipoNotificacion ;
    int idPersona;

    public Notificaciones(int idNotificacion, String detalle, int idTipoNotificacion, int idPersona ) {
        this.idNotificacion = idNotificacion;
        this.detalle = detalle;
        this.idTipoNotificacion = idTipoNotificacion;
        this.idPersona = idPersona;
    }
}
