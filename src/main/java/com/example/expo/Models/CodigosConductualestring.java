package com.example.expo.Models;

import lombok.Data;

@Data
public class CodigosConductualestring {
    int idCodigoConductual;
    String nivelCodigoConductual;
    String codigoConductual;

    public CodigosConductualestring(int idCodigoConductual, String nivelCodigoConductual, String codigoConductual) {
        this.idCodigoConductual = idCodigoConductual;
        this.nivelCodigoConductual = nivelCodigoConductual;
        this.codigoConductual = codigoConductual;

    }
}
