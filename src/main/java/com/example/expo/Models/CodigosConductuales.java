package com.example.expo.Models;

import lombok.Data;

@Data
public class CodigosConductuales {
    int idCodigoConductual;
    int idTipoCodigoConductual;
    int idNivelCodigoConductual;
    String codigoConductual;

    public CodigosConductuales(int idCodigoConductual, int idTipoCodigoConductual,int idNivelCodigoConductual,String codigoConductual){
        this.idCodigoConductual=idCodigoConductual;
        this.idTipoCodigoConductual=idTipoCodigoConductual;
        this.idNivelCodigoConductual=idNivelCodigoConductual;
        this.codigoConductual=codigoConductual;
    }
}
