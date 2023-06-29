package com.example.expo.Models;

import lombok.Data;

import java.util.Date;

@Data
public class TiposCodigosConductuales {
    int idTipoCodigoConductual;
    String tipoCodigoConductual;

    public TiposCodigosConductuales(int idTipoCodigoConductual, String tipoCodigoConductual){
        this.idTipoCodigoConductual = idTipoCodigoConductual;
        this.tipoCodigoConductual = tipoCodigoConductual;
    }
}
