package com.example.expo.Models;

import lombok.Data;

@Data
public class CodigoConductual {
   int  idCodigoConductual ;
   String TipoCodigoConductual;
    int IdNivelCodigoConductual;

    public CodigoConductual(String TipoCodigoConductual, int IdNivelCodigoConductual ) {
        this.TipoCodigoConductual = TipoCodigoConductual;
        this.IdNivelCodigoConductual = IdNivelCodigoConductual;
    }

}
