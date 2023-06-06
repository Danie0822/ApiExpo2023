package com.example.expo.Models;

import lombok.Data;

@Data
public class SeccionesBachillerato {
    int idSeccionBachillerato;
    String seccionBachillerato;

    public SeccionesBachillerato(int idSeccionBachillerato, String seccionBachillerato) {
        this.idSeccionBachillerato = idSeccionBachillerato;
        this.seccionBachillerato = seccionBachillerato;
    }
}
