package com.example.expo.Models;

import lombok.Data;

@Data
public class tbAdmi {
    int idAdmi;
    String tipoAdmi;

    public tbAdmi(int idAdmi, String tipoAdmi) {
        this.idAdmi = idAdmi;
        this.tipoAdmi = tipoAdmi;
    }
}
