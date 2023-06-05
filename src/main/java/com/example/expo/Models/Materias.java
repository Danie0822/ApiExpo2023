package com.example.expo.Models;

import lombok.Data;

@Data
public class Materias {
    int idMateria;
    String materia;

    public Materias() {
    }
    public Materias(int idMateria, String materia) {
        this.idMateria = idMateria;
        this.materia = materia;
    }
}
