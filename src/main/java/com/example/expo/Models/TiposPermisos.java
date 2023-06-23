package com.example.expo.Models;

import lombok.Data;

@Data
public class TiposPermisos {
    int idTipoPermiso;
    String tipoPermiso;

    public TiposPermisos(int idTipoPermiso, String tipoPermiso) {
        this.idTipoPermiso = idTipoPermiso;
        this.tipoPermiso = tipoPermiso;
    }
}
