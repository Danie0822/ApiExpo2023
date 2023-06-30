package com.example.expo.Models;

import lombok.Data;

@Data
public class TiposInasistencias {
    int idTipoInasistencia ;
    String tipoInasistencias;
    public TiposInasistencias (int idTipoInasistencia,String tipoInasistencias)
    {
        this.idTipoInasistencia = idTipoInasistencia;
        this.tipoInasistencias = tipoInasistencias;
    }
}
