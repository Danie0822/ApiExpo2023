package com.example.expo.Services;

import com.example.expo.Models.NivelesAcademicos;
import com.example.expo.Models.SeccionesBachillerato;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeccionesBachilleratoDB {
    static Connection _cn;

    public SeccionesBachilleratoDB(){
        _cn = new Conexion().openDB();
    }
    public List<SeccionesBachillerato> ObtenerSeccionesBachillerato(){
        try {
            Statement stnt = _cn.createStatement();
            String query = "select * from tbSeccionesBachillerato";

            List<SeccionesBachillerato> SeccionesBachillerato = new ArrayList<>();

            ResultSet result = stnt.executeQuery(query);

            while(result.next()){
                SeccionesBachillerato SeccionesBachillerato2 = new SeccionesBachillerato(
                        result.getInt("idSeccionBachillerato"),
                        result.getString("seccionBachillerato")
                );

                SeccionesBachillerato.add(SeccionesBachillerato2);

            }
            result.close();
            stnt.close();
            return SeccionesBachillerato;
        } catch (Exception e) {
            System.out.println("ocurrio una excepcion en Niveles Secciones Bachillerato");
            int x = 1;
        }
        return null;
    }

    public static int InsertarSeccionesBachillerato(SeccionesBachillerato SeccionesBachillerato){
        new SeccionesBachilleratoDB();
        String sql1 = "exec AgregarSeccionesBachillerato ?;";

        try {
            PreparedStatement statement = _cn.prepareStatement(sql1);
            statement.setString(1, SeccionesBachillerato.getSeccionBachillerato());
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
    public static int EliminarSeccionesBachillerato(int id){
        new SeccionesBachilleratoDB();
        String sql1 = "exec DeleteSeccionesBachillerato ?;";
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

    public static int ActulizarSeccionesBachillerato(SeccionesBachillerato SeccionesBachillerato){
        new SeccionesBachilleratoDB();

        String sql1 = "exec UpdateSeccionesBachilleratos ?, ?;";

        try {
            PreparedStatement statement = _cn.prepareStatement(sql1);
            statement.setString(1, SeccionesBachillerato.getSeccionBachillerato());
            statement.setInt(2, SeccionesBachillerato.getIdSeccionBachillerato());
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
