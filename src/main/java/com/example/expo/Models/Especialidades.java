package com.example.expo.Models;

import lombok.Data;

@Data
public class Especialidades {
    int idEspecialidad;
    String especialidad;

    public Especialidades(int idEspecialidad, String especialidad) {
        this.idEspecialidad = idEspecialidad;
        this.especialidad = especialidad;
    }
}
