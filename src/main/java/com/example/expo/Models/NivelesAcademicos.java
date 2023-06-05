package com.example.expo.Models;

import lombok.Data;

@Data
public class NivelesAcademicos {
    int idNivelAcademico;
    String nivelAcademico;

    public NivelesAcademicos() {
    }

    public NivelesAcademicos(int idNivelAcademico,String nivelAcademico) {
        this.nivelAcademico = nivelAcademico;
        this.idNivelAcademico = idNivelAcademico;
    }
}
