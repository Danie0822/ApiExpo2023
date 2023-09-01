package com.example.expo.Models;

import lombok.Data;

@Data
public class ReservacionesSalonesEstado {
    int idReservacionSalon;
    int Estado;

    public ReservacionesSalonesEstado(int idReservacionSalon, int Estado) {
        this.idReservacionSalon = idReservacionSalon;
        this.Estado = Estado;
    }
}
