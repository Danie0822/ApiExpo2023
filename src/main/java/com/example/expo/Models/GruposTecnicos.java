package com.example.expo.Models;

import lombok.Data;

@Data
public class GruposTecnicos {
    int idGrupoTecnico;
    String grupoTecnico;

    public GruposTecnicos(int idGrupoTecnico, String grupoTecnico) {
        this.idGrupoTecnico = idGrupoTecnico;
        this.grupoTecnico = grupoTecnico;
    }
}
