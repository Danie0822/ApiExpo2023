package com.example.expo.Models;

import lombok.Data;

@Data
public class Salones {
    int idSalon;
    String codigoSalon;

    public Salones(int idSalon,String codigoSalon) {
        this.codigoSalon = codigoSalon;
        this.idSalon = idSalon;
    }
}
