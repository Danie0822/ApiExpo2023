package com.example.expo.Models;

import lombok.Data;

@Data
public class GradosView {
    int idGrado;
    String idNivelAcademico;
    String idSeccion;
    String idSeccionBachillerato;
    String idDocenteEncargado;
    String idEspecialidad;
    String idGrupoTecnico;
    byte[] horario;

    public GradosView(){

    }

    public GradosView(int  idGrado, String  idNivelAcademico, String idSeccion,String idSeccionBachillerato, String idDocenteEncargado, String idEspecialidad, String idGrupoTecnico, byte[] horario ) {
        this.idGrado = idGrado;
        this.idNivelAcademico = idNivelAcademico;
        this.idSeccion = idSeccion;
        this. idSeccionBachillerato = idSeccionBachillerato;
        this.idDocenteEncargado = idDocenteEncargado;
        this. idEspecialidad = idEspecialidad;
        this.idGrupoTecnico  = idGrupoTecnico;
        this.horario = horario;
    }
}
