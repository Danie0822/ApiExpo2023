package com.example.expo.Models;

import lombok.Data;

@Data
public class Grados {
    int idGrado;
    int idNivelAcademico;
    int idSeccion;
    int idSeccionBachillerato;
    int idDocenteEncargado;
    int idEspecialidad;
    int idGrupoTecnico;
    byte[] horario;

    public Grados(){

    }

    public Grados(int idNivelAcademico, int idSeccion, int idSeccionBachillerato){
        this.idNivelAcademico = idNivelAcademico;
        this.idSeccion = idSeccion;
        this.idSeccionBachillerato = idSeccionBachillerato;
    }

    public Grados(int idEspecialidad, int idGrupoTecnico){
        this.idEspecialidad = idEspecialidad;
        this.idGrupoTecnico = idGrupoTecnico;
    }


    public Grados(int idGrado, int  idNivelAcademico, int idSeccion,int idSeccionBachillerato, int idDocenteEncargado, int idEspecialidad, int idGrupoTecnico, byte[] horario ) {
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
