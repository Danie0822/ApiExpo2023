package com.example.expo.Models;

import lombok.Data;

@Data
public class ReservacionesSalonestring {

    int idReservacionSalon;
    String Salon;
    String Inicio;
    String Final;
    String Reservante;
    String motivoReserva;
    int idReservante;
    int Estado;

    public ReservacionesSalonestring(int idReservacionSalon, String Salon, String Inicio, String Final, String Reservante, String motivoReserva, int idReservante, int Estado) {
        this.idReservacionSalon = idReservacionSalon;
        this.Salon = Salon;
        this.Inicio = Inicio;
        this.Final = Final;
        this.Reservante = Reservante;
        this.motivoReserva = motivoReserva;
        this.idReservante = idReservante;
        this.Estado = Estado;
    }
}
