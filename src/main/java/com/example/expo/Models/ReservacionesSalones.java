package com.example.expo.Models;

import lombok.Data;

@Data
public class ReservacionesSalones {
    int idReservacionSalon;
    int idSalon;
    int idRangoHora;
    int idReservante;
    String motivoReserva;

    public ReservacionesSalones(int idReservacionSalon, int idSalon, int idRangoHora, int idReservante, String motivoReserva) {
        this.idReservacionSalon = idReservacionSalon;
        this.idSalon = idSalon;
        this.idRangoHora = idRangoHora;
        this.idReservante = idReservante;
        this.motivoReserva = motivoReserva;
    }
}
