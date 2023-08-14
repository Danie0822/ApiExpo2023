package com.example.expo.Models;

import lombok.Data;

@Data
public class InasisitenciasEstado {
    int estado;
    int idInasistencia;



    public InasisitenciasEstado(int estado, int idInasistencia) {
        this.estado = estado;
        this.idInasistencia = idInasistencia;
    }
}
