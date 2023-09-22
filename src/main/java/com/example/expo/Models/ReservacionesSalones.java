package com.example.expo.Models;

import lombok.Data;

@Data
public class ReservacionesSalones {
    int idReservacionSalon;
    int idSalon;
    int idRangoHora;
    int idReservante;
    String motivoReserva;
    int Estado ;
    String Fecha;

    public ReservacionesSalones(int idReservacionSalon, int idSalon, int idRangoHora, int idReservante, String motivoReserva, int Estado, String Fecha) {
        this.idReservacionSalon = idReservacionSalon;
        this.idSalon = idSalon;
        this.idRangoHora = idRangoHora;
        this.idReservante = idReservante;
        this.motivoReserva = motivoReserva;
        this.Estado = Estado;
        this.Fecha = Fecha;
    }
}
