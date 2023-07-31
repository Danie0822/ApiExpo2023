package com.example.expo.Models;


import lombok.Data;

@Data
public class Matriculas {

    int idMatricula;
    int idEstudiante;
    int idGradoAcademico;
    int idGradoTecnico;
    byte[] horario;

    public Matriculas(int idMatricula, int idEstudiante, int idGradoAcademico, int idGradoTecnico, byte[] horario){
        this.idMatricula = idMatricula;
        this.idEstudiante = idEstudiante;
        this.idGradoAcademico = idGradoAcademico;
        this.idGradoTecnico = idGradoTecnico;
    }

    public Matriculas(){

    }

}
