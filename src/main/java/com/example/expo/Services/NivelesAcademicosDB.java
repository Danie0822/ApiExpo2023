package com.example.expo.Services;

import com.example.expo.Models.NivelesAcademicos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NivelesAcademicosDB {
    static Connection _cn;

    public NivelesAcademicosDB(){
        _cn = new Conexion().openDB();
    }
    public List<NivelesAcademicos> ObtenerNivelesAcademicos(){
        try {
            Statement stnt = _cn.createStatement();
            String query = "select * from tbNivelesAcademicos";

            List<NivelesAcademicos> NivelesAcademicos = new ArrayList<>();

            ResultSet result = stnt.executeQuery(query);

            while(result.next()){
                NivelesAcademicos NivelesAcademicos2 = new NivelesAcademicos(
                        result.getInt("idNivelAcademico"),
                        result.getString("nivelAcademico")
                );

                NivelesAcademicos.add(NivelesAcademicos2);

            }
            result.close();
            stnt.close();
            return NivelesAcademicos;
        } catch (Exception e) {
            System.out.println("ocurrio una excepcion en mascotasDB");
            int x = 1;
        }
        return null;
    }

    public static int InsertarNivelesAcademicos(NivelesAcademicos NivelesAcademicos){
        new NivelesAcademicosDB();
        String sql1 = "exec AgregarNivelesAcademicos ?;";

        try {
            PreparedStatement statement = _cn.prepareStatement(sql1);
            statement.setString(1, NivelesAcademicos.getNivelAcademico());
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
    public static int EliminarNivelesAcademicos(int id){
        new NivelesAcademicosDB();
        String sql1 = "exec DeleteNivelesAcademicos ?;";
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

    public static int ActulizarNivelesAcademicos(NivelesAcademicos NivelesAcademicos){
        new NivelesAcademicosDB();

        String sql1 = "exec UpdateNivelesAcademicos ?, ?;";

        try {
            PreparedStatement statement = _cn.prepareStatement(sql1);
            statement.setString(1, NivelesAcademicos.getNivelAcademico());
            statement.setInt(2, NivelesAcademicos.getIdNivelAcademico());
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

