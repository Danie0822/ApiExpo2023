package com.example.expo.Services;

import com.example.expo.Models.GruposTecnicos;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GruposTecnicosDB {

    static Connection _cn;

    public GruposTecnicosDB(){
        _cn = new Conexion().openDB();
    }
    public List<GruposTecnicos> ObtenerGruposTecnicos(){
        try {
            Statement stnt = _cn.createStatement();
            String query = "select * from tbGruposTecnicos";

            List<GruposTecnicos> GruposTecnicos = new ArrayList<>();

            ResultSet result = stnt.executeQuery(query);

            while(result.next()){
                GruposTecnicos GruposTecnicos2 = new GruposTecnicos(
                        result.getInt("idGrupoTecnico"),
                        result.getString("grupoTecnico")
                );

                GruposTecnicos.add(GruposTecnicos2);

            }
            result.close();
            stnt.close();
            return GruposTecnicos;
        } catch (Exception e) {
            System.out.println("ocurrio una excepcion en Grupo Tecnico");
            int x = 1;
        }
        return null;
    }

    public static int InsertarGruposTecnicos(GruposTecnicos GruposTecnicos){
        new GruposTecnicosDB();
        String sql1 = "exec AgregarGruposTecnicos ?;";

        try {
            PreparedStatement statement = _cn.prepareStatement(sql1);
            statement.setString(1, GruposTecnicos.getGrupoTecnico());
            try {
                statement.executeUpdate();
                _cn.close();
                statement.close();
                return 1;
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return 0;
    }
    public static int EliminarGruposTecnicos(int id){
        new GruposTecnicosDB();
        String sql1 = "exec DeleteGruposTecnicos ?;";

        try {
            PreparedStatement statement = _cn.prepareStatement(sql1);
            statement.setInt(1, id);

            try {
                statement.executeUpdate();
                _cn.close();
                statement.close();
                return 1;
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return 0;



    }

    public static int Actulizar(GruposTecnicos GruposTecnicos){
        new GruposTecnicosDB();

        String sql1 = "exec UpdateGruposTecnicos ?, ?;";

        try {
            PreparedStatement statement = _cn.prepareStatement(sql1);
            statement.setString(1, GruposTecnicos.getGrupoTecnico());
            statement.setInt(2, GruposTecnicos.getIdGrupoTecnico());
            try {
                statement.executeUpdate();
                _cn.close();
                statement.close();
                return 1;
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return 0;
    }
}
