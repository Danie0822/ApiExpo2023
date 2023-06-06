package com.example.expo.Services;

import com.example.expo.Models.Salones;
import com.example.expo.Models.SeccionesBachillerato;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalonesDB {
    static Connection _cn;

    public SalonesDB(){
        _cn = new Conexion().openDB();
    }
    public List<Salones> ObtenerSalones(){
        try {
            Statement stnt = _cn.createStatement();
            String query = "select * from tbSalones";

            List<Salones> Salones = new ArrayList<>();

            ResultSet result = stnt.executeQuery(query);

            while(result.next()){
                Salones Salones2 = new Salones(
                        result.getInt("idSalon"),
                        result.getString("codigoSalon")
                );

                Salones.add(Salones2);

            }
            result.close();
            stnt.close();
            return Salones;
        } catch (Exception e) {
            System.out.println("ocurrio una excepcion en Niveles Salones");
            int x = 1;
        }
        return null;
    }

    public static int InsertarSalones(Salones Salones){
        new SalonesDB();
        String sql1 = "exec AgregarSalones ?;";

        try {
            PreparedStatement statement = _cn.prepareStatement(sql1);
            statement.setString(1, Salones.getCodigoSalon());
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
    public static int EliminarSalones(int id){
        new SalonesDB();
        String sql1 = "exec DeleteSalones ?;";
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

    public static int ActulizarSalones(Salones Salones){
        new SalonesDB();

        String sql1 = "exec UpdateSalones ?, ?;";

        try {
            PreparedStatement statement = _cn.prepareStatement(sql1);
            statement.setString(1, Salones.getCodigoSalon());
            statement.setInt(2, Salones.getIdSalon());
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
