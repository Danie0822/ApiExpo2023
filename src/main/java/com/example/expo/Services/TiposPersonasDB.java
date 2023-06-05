package com.example.expo.Services;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.example.expo.Models.TiposPersonas;


public class TiposPersonasDB {
   static Connection _cn;

    public TiposPersonasDB(){
        _cn = new Conexion().openDB();
    }

    public List<TiposPersonas> obtenerMascotas(){
        try {
            Statement stnt = _cn.createStatement();
            String query = "select * from tbTiposPersonas";

            List<TiposPersonas> TiposPersonas = new ArrayList<>();

            ResultSet result = stnt.executeQuery(query);

            while(result.next()){
                TiposPersonas TiposPersonas2 = new TiposPersonas(
                    result.getInt("idTipoPersona"),
                    result.getString("tipoPersona")
                );

                TiposPersonas.add(TiposPersonas2);

            }
            result.close();
            stnt.close();
            return TiposPersonas;
        } catch (Exception e) {
            System.out.println("ocurrio una excepcion en mascotasDB");
            int x = 1;
        }
        return null;
    }

    public static int Insertar(TiposPersonas TiposPersonas){
        new TiposPersonasDB();
        String sql1 = "exec AgregarTiposPersonas ?;";

        try {
            PreparedStatement statement = _cn.prepareStatement(sql1);
            statement.setString(1, TiposPersonas.getTipoPersona());
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
    public static int Eliminar(int id){
        new TiposPersonasDB();
        String sql1 = "exec DeleteTiposPersonas ?;";

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

    public static int Actulizar(TiposPersonas TiposPersonas){
        new TiposPersonasDB();

        String sql1 = "exec UpdateTiposPersonas ?, ?;";

        try {
            PreparedStatement statement = _cn.prepareStatement(sql1);
            statement.setString(1, TiposPersonas.getTipoPersona());
            statement.setInt(2, TiposPersonas.getIdTipoPersona());
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
